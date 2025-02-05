# Análise de Rendimentos de Carteira de Investimentos / Investment Portfolio Performance Analysis
## Descrição / Description
Este projeto tem como objetivo realizar a análise de rentabilidade de uma carteira de investimentos, utilizando dados históricos de preços de ações. A partir de um arquivo de texto com os ativos e as quantidades de cada ação, o script em Python obtém as cotações de cada ativo e calcula a rentabilidade da carteira, comparando com o índice Ibovespa (IBOV).

This project aims to analyze the performance of an investment portfolio by retrieving historical price data of the stocks. Using a text file with the assets and their respective quantities, the Python script fetches the stock prices and computes the portfolio's performance, comparing it to the Ibovespa (IBOV) index.

## Requisitos / Requirements
+ Python 3.x
+ pandas: Para manipulação de dados.
+ pandas_datareader: Para obter dados financeiros.
+ yfinance: Para download das cotações históricas.

### You will need the following Python packages:
+ Python 3.x
+ pandas: For data manipulation.
+ pandas_datareader: To fetch financial data.
+ yfinance: For downloading historical stock prices.
  
## Como usar / How to use
Preparar a carteira de investimentos: Crie um arquivo de texto chamado carteira.txt no mesmo diretório do script. O arquivo deve conter o código da ação (ticker) e a quantidade de cada ativo, separados por um hífen (-), no seguinte formato:

Prepare the investment portfolio: Create a text file named carteira.txt in the same directory as the script. The carteira.txt file should contain the ticker and the number of shares for each asset, separated by a hyphen (-), like this:


## Exemplo / Example:

Copiar código / Copy Code:

```yaml
- ITUB4 - 1000
- BBAS3 - 2000
- VALE3 - 1000
- EGIE3 - 500
- SLCE3 - 300
```

Execute o arquivo codigo.ipynb no Jupyter Notebook. Ele irá carregar os dados, calcular as rentabilidades dos ativos e da carteira como um todo, e comparar com a rentabilidade do índice Ibovespa (IBOV).

Run the codigo.ipynb script in Jupyter Notebook. It will load the data, calculate the returns for each asset and the portfolio as a whole, and compare it to the performance of the Ibovespa (IBOV) index.

## Estrutura do Código:
+ **carteira.txt**: Arquivo com a lista de ativos e quantidades da carteira.
+ **codigo.ipynb**: Jupyter Notebook com o código Python que realiza a análise.

### Code Structure:
+ **codigo.ipynb**: File with the list of assets and quantities in the portfolio. 
+ **codigo.ipynb**: Jupyter Notebook containing the Python code that performs the analysis.

## Passos principais do código:

+ Leitura do arquivo carteira.txt e criação de um dicionário com os ativos e suas quantidades.
+ Download das cotações históricas dos ativos da carteira e do índice Ibovespa.
+ Cálculo das rentabilidades individuais de cada ativo e da rentabilidade total da carteira.
+ Comparação da rentabilidade da carteira com a rentabilidade do índice Ibovespa.

### Key steps of the code

+ Read the carteira.txt file and create a dictionary with the assets and their quantities.
+ Download historical stock prices for the assets in the portfolio and the Ibovespa index.
+ Calculate the individual returns for each asset and the overall portfolio return.
+ Compare the portfolio's performance with the performance of the Ibovespa index.

## Exemplo de Saída / Example Output
Após a execução do código, a saída mostrará:

+ Rentabilidade individual de cada ativo.
+ Rentabilidade total da carteira.
+ Rentabilidade do índice Ibovespa (IBOV) para o mesmo período.
  
After running the code, the output will show:

+ Individual returns for each asset.
+ Total portfolio return.
+ Performance of the Ibovespa (IBOV) index for the same period.

## Licença / License
Este projeto está licenciado sob a licença MIT.

This project is licensed under the MIT License.
