import java.text.NumberFormat;

/** 
 * MIT License
 *
 * Copyright(c) 2022-4 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


 /**
  * Classe Comida para demonstração de conceitos de POO Básica. 
  * Aborda visibilidade, encapsulamento, construtores. A ser melhorada no futuro.
  */
public class Comida {

    //#region atributos
	private String descricao;
    private double precoBase;
	private double valorPorAdicional;
    private int maxAdicionais;
    private int qtdAdicionais;
	
	
    //#endregion


    /**
     * Construtor para uma comida sem adicionais. Recebe o tipo de comida (pizza ou sanduíche)
     * Em caso de tipo inválido, cria um sanduíche.
     * @param descricao String "Pizza" ou "Sanduiche" (<i>case-insensitive</i>). Tipo inválido cria um sanduíche.
     */
    public Comida(String descricao){
        init(descricao, 0);
    }

    /**
     * Construtor para uma comida com adicionais. Recebe o tipo de comida (pizza ou sanduíche) e a quantidade de adicionais.
     * O máximo de adicionais é 5 para sanduíche e 8 para pizza. O objeto fica com 0 adicionais se os limites não forem respeitados.
     * Em caso de tipo inválido, cria um sanduíche.
     * @param tipo String "Pizza" ou "Sanduiche" (<i>case-insensitive</i>). Tipo inválido cria um sanduíche.
     * @param adicionais Inteiro com a quantidade de adicionais (0 a 5 (sanduíche) ou 8 (pizza))
     */
    public Comida(String tipo, int adicionais){
        init(tipo, adicionais);
     }


    /**
     * Inicializador para os diversos construtores. Recebe o tipo de comida (pizza ou sanduíche) e a quantidade de adicionais.
     * O máximo de adicionais é 5 para sanduíche e 8 para pizza. O objeto fica com 0 adicionais se os limites não forem respeitados.
     * Em caso de tipo inválido, cria um sanduíche.
     * @param descricao String "Pizza" ou "Sanduiche" (<i>case-insensitive</i>). Tipo inválido cria um sanduíche.
     * @param qtdExtras Inteiro com a quantidade de adicionais (0 a 5 (sanduíche) ou 8 (pizza))
     */
    private void init(String descricao, int qtdExtras){
        descricao = descricao.toUpperCase();
        switch(descricao){
            case "PIZZA":
                descricao = "PIZZA";
                maxAdicionais = 8;
                precoBase = 25.0;
                valorPorAdicional = 4.0;
            break;
            case "SANDUICHE":
            default:
                descricao = "SANDUÍCHE";
                maxAdicionais = 5;
                precoBase = 13.0;
                valorPorAdicional = 2.0;
            break;
            
        }
        adicionarIngredientes(qtdExtras);
    }

    /**
     * Retorna o calculo do valor dos ingredientes adicionas da comida.
     * @return valor (double) dos ingredientes para ser somado no preço base.
     */
	private double valorDosAdicionais() {

		return qtdAdicionais * valorPorAdicional;
	}
    
    /**
     * Validação interna da quantidade de ingredientes adicionais. Para a resposta ser positiva, o valor precisa ser
     * não-negativo e menor ou igual ao máximo definido para a comida. A conta é feita a partir da soma da quantidade
     * atual com a quantidade recebida por parâmetro.
     * @param quantos Quantos ingredientes a inserir ou retirar da comida
     * @return TRUE se a quantidade permanece válida, FALSE caso contrário
     */
	private boolean podeAdicionarIngredientes(int quantos) {
		return ( quantos>0 && qtdAdicionais+quantos<=maxAdicionais );
	}


    //TO-DO
	public double precoFinal() {
        return precoBase + valorDosAdicionais();
		
	}
    

    /**
     * Relatório para a comida. Formatação em 4 linhas, com <ul>
     * <li> Descrição da comida e adicionais </li>
     * <li> Preço inicial da comida </li>
     * <li> Valor pago pelos ingredientes adicionais </li>
     * <li> Preço total a ser pago pela comida </li>
     * <ul>
     * @return Uma string contendo o relatório de venda da comida conforme descrição acima. 
     */
	public String relatorio() {
        NumberFormat moeda = NumberFormat.getCurrencyInstance();

		StringBuilder aux = new StringBuilder(descricao);
        aux.append(" COM "+qtdAdicionais+" ADICIONAIS.\n");
        aux.append("    Preço inicial: "+moeda.format(precoBase)+"\n");
        aux.append("    Preço dos adicionais: "+moeda.format(valorDosAdicionais())+"\n");
        aux.append("PREÇO A PAGAR: "+moeda.format(precoFinal()));

        return aux.toString();

	}

    /**
     * Tenta adicionar ingredientes à comida. O parâmetro deve ser um número inteiro não-negativo. Retorna a quantidade de adicionais
     * existentes na comida após completar a operação.
     * @param quantos Quantidade de ingredientes a adicionar (>=0)
     * @return A quantidade de adicionais existentes na comida após a operação. Esta quantidade pode ser idêntica ao que já existia,
     * se houver problemas na validação.
     */
	public int adicionarIngredientes(int quantos) {
        if(podeAdicionarIngredientes(quantos)){
            qtdAdicionais += quantos;
        } 
        return qtdAdicionais;
	}

}
