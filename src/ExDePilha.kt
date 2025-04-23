import java.util.LinkedList

class ExDePilha {

    var pilha = LinkedList<Produto>()

    // Início do programa
    fun iniciar() {

        while (true) {
            if (pilha.isEmpty()) {
                Thread.sleep(2000) // usada somente para melhor visualização no teste
                adicionarProdutos()
            }
            remover() // remove os produtos
        }
    }

    private fun adicionarProdutos() {
        val tempoAtual = System.currentTimeMillis() // Guarda o ponto exato em que o código é executado,
        // com isso consigo calcular o vencimento

        // coloco os produtos dentro da lista
        pilha.push(Produto("Leite", 1 * 1000, 6, tempoAtual))
        // 4 * 60 * 1000 converte 4 minutos para 60 segundos e de 60 segundos para 1000 milisegundos
        pilha.push(Produto("Cerveja", 4 * 60 * 1000, 10, tempoAtual))
        pilha.push(Produto("Salgadinho", 3 * 60 * 1000, 5, tempoAtual))

        println("Produtos foram adicionados à pilha.")
    }

    private fun remover() {
        // Guarda a quantidade de removidos para fazer a verificação abaixo
        var removidos = 0

        // verifica se há o que remover
        while (pilha.isNotEmpty()) {
            // olha o objeto que está no topo da pilha e então 'produto' passa a referenciar esse elemento
            // que vai ser checado e removido em sequência
            val produto = pilha.peek()

            // devolve o tempo que passou em milissegundos
            val tempoAtual = System.currentTimeMillis()

            // pega o tempo atual e compara se o tempo pegado anteriormente + a validade cria um timestamp
            // uma etiqueta temporal. Então, se o tempoAtual for maior, ele está no tempo à frente do vencimento
            // do produto. Logo, ele está vencido.
            if (tempoAtual > produto.tempoAdicao + produto.validade) {
                Thread.sleep(1000) // usada somente para melhor visualização no teste
                println("Produto ${produto.nome} (Quantidade: ${produto.quantidade}) está vencido! Removendo...")
                pilha.pop()
                Thread.sleep(2000) // usada somente para melhor visualização no teste
                println("Removido.")
            } else {
                Thread.sleep(1000) // usada somente para melhor visualização no teste
                println("Removendo ${produto.nome} (Quantidade: ${produto.quantidade})")
                pilha.pop()
                removidos++

                Thread.sleep(produto.quantidade * 1000L) // simula tempo proporcional à quantidade
                println("Removido.")
            }
        }
    }
}
