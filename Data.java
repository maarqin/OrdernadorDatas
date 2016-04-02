
/**
 * Classe data
 * 
 * @author thomaz 
 */
public class Data
{
    public Data(Integer ano, Integer mes, Integer dia) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }
    
    public Integer getAno() {
        return this.ano;
    }
    
    public Integer getMes() {
        return this.mes;
    }
    
    public Integer getDia() {
        return this.dia;
    }
    
    @Override
    public String toString() {
        return this.dia + "/" + this.mes + "/" + this.ano;
    }
    
    private Integer ano, mes, dia;
}
