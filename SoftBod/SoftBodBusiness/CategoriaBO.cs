using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftBodWSServices;

namespace SoftBodBusiness
{
    public class CategoriaBO
    {
        private CategoriaClient categoriaSOAP;

        public CategoriaBO()
        {
            categoriaSOAP = new CategoriaClient();
        }

        public int insertarCategoria(string descripcion)
        {
            return categoriaSOAP.insertarCategoria(descripcion);
        }

        public int eliminarCategoria(categoriaDTO categoria)
        {
            return categoriaSOAP.eliminarCategoria(categoria);
        }

        public categoriaDTO obtenerCategoriaPorId(int categoriaId)
        {
            return categoriaSOAP.obtenerCategoriaPorId(categoriaId);
        }

        public List<categoriaDTO> listarTodasCategorias()
        {
            return categoriaSOAP.listarTodasCategorias()?.ToList()
                   ?? new List<categoriaDTO>();
        }

        public bool categoriaContieneProductos(int categoriaId)
        {
            return categoriaSOAP.categoriaContieneProductos(categoriaId);
        }

        public bool puedeEliminarCategoria(int categoriaId)
        {
            return categoriaSOAP.puedeEliminarCategoria(categoriaId);
        }

        public bool nombreCategoriaYaExiste(string descripcion)
        {
            return categoriaSOAP.nombreCategoriaYaExiste(descripcion);
        }
    }
}