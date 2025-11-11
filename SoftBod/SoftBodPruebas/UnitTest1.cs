using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftBodBusiness;
using SoftBodBusiness.SoftWSDevolucion;
using System;
using System.Collections.Generic;
using System.ServiceModel;
using System.ServiceModel.Channels;
using System.ServiceModel.Description;
using System.ServiceModel.Dispatcher;
using System.Text;
using System.Xml;

namespace SoftBodPruebas
{
    //[TestClass]
    public class DevolucionBOTest
    {
        [TestMethod]
        public void PruebasDevolucion()
        {
            DevolucionClient cliente = new DevolucionClient();
            cliente.Endpoint.EndpointBehaviors.Add(new SoapInspectorBehavior());

            var user = new usuarioDTO();
            user.usuarioId = 2;
            user.usuarioIdSpecified = true;

            List<detalleDevolucionDTO> detallesDev = new List<detalleDevolucionDTO>();

            for (int i = 1; i <= 4; i++)
            {
                var detalleDev = new detalleDevolucionDTO();

                detalleDev.razonDevolucion = new razonDevolucionDTO();
                detalleDev.razonDevolucion.razonDevolucionId = 1;
                detalleDev.razonDevolucion.razonDevolucionIdSpecified = true;

                detalleDev.subtotal = 10.90;
                detalleDev.subtotalSpecified = true;

                detalleDev.cantidad = 4;
                detalleDev.cantidadSpecified = true;

                detalleDev.producto = new productoDTO();
                detalleDev.producto.productoId = i;
                detalleDev.producto.productoIdSpecified = true;

                detallesDev.Add(detalleDev);
            }

            int nuevaDev = cliente.insertarDevolucion(user, detallesDev.ToArray());
            Console.WriteLine("Nueva Devolucion con id: " + nuevaDev);
            Assert.IsTrue(nuevaDev > 0);

            cliente.Close();
        }
    }

    public class SoapInspectorBehavior : IEndpointBehavior
    {
        public void AddBindingParameters(ServiceEndpoint endpoint, BindingParameterCollection bindingParameters) { }

        public void ApplyClientBehavior(ServiceEndpoint endpoint, ClientRuntime clientRuntime)
        {
            clientRuntime.MessageInspectors.Add(new SoapInspector());
        }

        public void ApplyDispatchBehavior(ServiceEndpoint endpoint, EndpointDispatcher endpointDispatcher) { }

        public void Validate(ServiceEndpoint endpoint) { }
    }

    public class SoapInspector : IClientMessageInspector
    {
        public void AfterReceiveReply(ref Message reply, object correlationState)
        {
            Console.WriteLine("\n=== RESPONSE ===");
            Console.WriteLine(GetXml(ref reply));
        }

        public object BeforeSendRequest(ref Message request, IClientChannel channel)
        {
            Console.WriteLine("\n=== REQUEST ===");
            Console.WriteLine(GetXml(ref request));
            return null;
        }

        private string GetXml(ref Message message)
        {
            MessageBuffer buffer = message.CreateBufferedCopy(Int32.MaxValue);
            message = buffer.CreateMessage();

            StringBuilder sb = new StringBuilder();
            XmlWriterSettings settings = new XmlWriterSettings();
            settings.Indent = true;

            using (XmlWriter writer = XmlWriter.Create(sb, settings))
            {
                buffer.CreateMessage().WriteMessage(writer);
            }

            return sb.ToString();
        }
    }
}