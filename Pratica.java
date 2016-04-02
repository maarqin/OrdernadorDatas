
/**
 * Classe para testar a aplicacao
 * 
 * @author thomaz
 */
public class Pratica
{
	public static void main(String[] args) {

		OrdenadorDatas od = OrdenadorDatas.gerarListaAleatoria(20);        

		System.out.println("Counting sort\n");
		
		OrdenadorDatas.Ordem[] dma = { OrdenadorDatas.Ordem.DIAS, OrdenadorDatas.Ordem.MESES, OrdenadorDatas.Ordem.ANOS };
		OrdenadorDatas.Ordem[] amd = { OrdenadorDatas.Ordem.ANOS, OrdenadorDatas.Ordem.MESES, OrdenadorDatas.Ordem.DIAS };
		OrdenadorDatas.Ordem[] adm = { OrdenadorDatas.Ordem.ANOS, OrdenadorDatas.Ordem.DIAS, OrdenadorDatas.Ordem.MESES };

        od.ordenarPor(dma);
        System.out.println("Ordenado por dia, mes e ano:\n" + od.toString());
        System.out.println("\n-------\n");

        
        od.ordenarPor(amd);
        System.out.println("Ordenado por ano, mes e dia:\n" + od.toString());
        System.out.println("\n-------\n");
        
        od.ordenarPor(adm);
        System.out.println("Ordenado por ano, dia e mes:\n" + od.toString());
        
        
        System.out.println("\nRadix sort\n");
        

        od.ordenarPorRadix(dma);
        System.out.println("Ordenado por dia, mes e ano:\n" + od.toString());
        System.out.println("\n-------\n");

        
        od.ordenarPorRadix(amd);
        System.out.println("Ordenado por ano, mes e dia:\n" + od.toString());
        System.out.println("\n-------\n");
        
        od.ordenarPorRadix(adm);
        System.out.println("Ordenado por ano, dia e mes:\n" + od.toString());

	}

	private Pratica() {}
}
