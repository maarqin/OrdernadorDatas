import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe p/ ordenacao dinamica de uma lista com data(yyy/MM/dd).
 *
 * @author thomaz
 */
public class OrdenadorDatas
{
    
    /**
     * Construtor da classe
     * 
     */
    private OrdenadorDatas() {
        datas = new ArrayList<Data>();
    }
    
    /**
     * Ordena a data por dia
     * 
     */
    public void ordenarPorDias() {
    	ordenarPor(Ordem.DIAS);
    }
    
    /**
     * Ordena a data por mes
     * 
     */
    public void ordenarPorMeses() {
    	ordenarPor(Ordem.MESES);
    }
    
    /**
     * Ordena a data por ano
     * 
     */
    public void ordenarPorAnos() {
    	ordenarPor(Ordem.ANOS);
    }
    
    /**
     * Metodo que ordena as datas que estão na lista por CountingSort
     * Define as prioridades para a ordenacao
     * 
     * @param ordem arranjo contendo a ordem de como deverá ser ordenado as datas
     */
    public void ordenarPor(Ordem[] ordem) {

    	for (int i = ordem.length - 1; i >= 0; i--) {
			if( Ordem.ANOS == ordem[i] ){
				ordenarPorAnos();
			} else if ( Ordem.MESES == ordem[i] ) {
				ordenarPorMeses();
			} else {
				ordenarPorDias();
			}
		}

    }
    
    /**
     * Metodo que ordena as datas que estão na lista por RadixSort
     * Define as prioridades para a ordenacao
     * 
     * @param ordem arranjo contendo a ordem de como deverá ser ordenado as datas
     */
    public void ordenarPorRadix(Ordem[] ordem) {
    	
    	for (int i = ordem.length - 1; i >= 0; i--) {
			if( Ordem.ANOS == ordem[i] ){
				ordenarPorRadix(Ordem.ANOS);
			} else if ( Ordem.MESES == ordem[i] ) {
				ordenarPorRadix(Ordem.MESES);
			} else {
				ordenarPorRadix(Ordem.DIAS);
			}
		}
    }

    /**
     * Ordena por ANO, MES ou DIA
     * 
     * @param ordem
     */
    private void ordenarPor(Ordem ordem) {
    	
    	int iMaior = 0;
    	for (Data data : datas) {
    		Integer i = valorDeData(data, ordem);
    		
    		if( i > iMaior ) iMaior = i;
		}
    	
    	int[] counted = new int[iMaior + 1];
    	
    	for (Data data : datas) {
    		Integer i = valorDeData(data, ordem);
			counted[i]++;
		}
    	
    	for (int i = 0; i < counted.length - 1; i++) {
			counted[i + 1] = counted[i] + counted[i + 1];
		}
    	
    	List<Data> list = new ArrayList<>(datas); 
    	for (int i = list.size() - 1; i >= 0; i--) {	
			Data data = list.get(i);
			Integer j = valorDeData(data, ordem);
    		
			Integer k = --counted[j];
			
			datas.set(k, data);
		}
    }
    
    /**
     * Identifica e retorna um valor de acordo com o atributo da classe {@link Data}
     * 
     * @param data
     * @param ordem
     * @return
     */
    private Integer valorDeData(Data data, Ordem ordem){
    	if( Ordem.ANOS == ordem ) return data.getAno();
    	else if( Ordem.MESES == ordem ) return data.getMes();
    	else return data.getDia();
    }
    
    /**
     * Ordena por ANO, MES ou DIA
     * 
     * @param ordem
     */
    private void ordenarPorRadix(Ordem ordem) {
    	int at = 1;
    	
    	int[] counted = new int[10];

    	for (int i = 0; i < ordem.length; i++) {

	    	for (Data data : datas) {
	    		Integer j = sAt(data, ordem, at);
	    		
				counted[j]++; 
	    	}
	    	
	    	for (int j = 0; j < counted.length - 1; j++) {
				counted[j + 1] = counted[j] + counted[j + 1];
			}
    			
	    	List<Data> list = new ArrayList<>(datas); 
	    	for (int j = list.size() - 1; j >= 0; j--) {	
				Data data = list.get(j);
				Integer k = sAt(data, ordem, at);
	    		
				Integer l = --counted[k];
				
				datas.set(l, data);
	    	}
	    	counted = new int[10];

			at++;
    	}
    	
    }
    	
    /**
     * Metodo que concatena ou nao(0 a um int < 9) e retorna esse valor
     * 
     * @param data
     * @param ordem
     * @return
     */
    private Integer sAt(Data data, Ordem ordem, int at){
    	Integer valor = valorDeData(data, ordem);
    	String dString = null;
    	if( valorDeData(data, ordem) < 10 ){
    		StringBuilder b = new StringBuilder();
    		b.append("0");
    		b.append(valorDeData(data, ordem));
    		dString = b.toString();
    	} else {
    		dString = valor.toString();    		
    	}
		
		int len = (dString.length() - at); 
		return Integer.parseInt(dString.charAt(len) + "");
    }
    
    /**
     * Metodo para gerar datas ficticias
     * 
     * @param numDatas Numero de datas que voce deseja que o metodo gere
     * @return Retorna uma instancia da classe contendo uma lista de datas a serem ordenadas
     */
    public static OrdenadorDatas gerarListaAleatoria(int numDatas) {
        Integer[] anos = { 1500, 1918, 1938, 1945, 1964, 1981, 1994 };
        Integer[] meses = { 1, 3, 5, 8, 10, 12 };
        Integer[] dias = { 10, 17, 18, 23, 28, 31 };
        
        OrdenadorDatas od = new OrdenadorDatas();
        do {
            Integer ano = anos[r.nextInt(anos.length)];
            Integer mes = meses[r.nextInt(meses.length)];
            Integer dia = dias[r.nextInt(dias.length)];
            od.inserirData(new Data(ano, mes, dia));
            
            numDatas--;
        } while (numDatas > 0);
        
        return od;
    }
    
    /**
     * Insere uma data à lista de datas
     * 
     * @param d
     */
    private void inserirData(Data d) {
        datas.add(d);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Data d: datas) {
            sb.append(d).append("\n");
        }
        return sb.toString();
    }
    
    /**
     * @author thomaz
     * 
     * Define as constantes para cada atributo de uma data
     */
    public enum Ordem {
        ANOS(4), MESES(2), DIAS(2);
    	
    	private int length;
    	
    	private Ordem(int length) {
    		this.length = length;
		}
    }
    
    private List<Data> datas;
    
    // Números aleatórios com seed constante para todos termos os mesmos resultados
    private final static Random r = new Random(500);
}
