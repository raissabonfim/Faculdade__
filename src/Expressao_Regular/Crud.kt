package Expressao_Regular

data class Convidado(
    var nome: String = "",
    var presente: String = "",
    var alimentar: String = "",
    var presenca: Boolean = false

)

val listaConvidados = mutableListOf<Convidado>()

fun main() {
    menuConvidados()
}

private fun menuConvidados() {
    var opcao: Int
    do {
        println("\n--- MENU ---")
        println("1 - Cadastrar")
        println("2 - Listar")
        println("3 - Editar")
        println("4 - Excluir")
        println("5 - Buscar")
        println("0 - Sair")

        opcao = readlnOrNull()?.toIntOrNull() ?: -1

        when (opcao) {
            1 -> cadastrar()
            2 -> listar()
            3 -> editar()
            4 -> excluir()
            5 -> buscar()
            0 -> println("Saindo...")
            else -> println("Opção inválida! Digite um número de 0 a 5.")
        }
    } while (opcao != 0)
}

private fun cadastrar() {
    val regexNome = Regex("^[A-Za-zÀ-ÿ ]+$")
    val convidado = Convidado()
    do {
        print("Digite o nome: ")
        convidado.nome = readlnOrNull()?.trim() ?: ""
        if (!convidado.nome.matches(regexNome)) {
            println("Nome inválido! Apenas letras e espaços são permitidos.")
        }
    } while (!convidado.nome.matches(regexNome))

    print("Qual presente você levará? ")
    convidado.presente = readlnOrNull()?.trim() ?: ""

    print("Possui alguma restrição alimentar? ")
    convidado.alimentar = readlnOrNull()?.trim() ?: ""

    var presencaInput: String
    do {
        print("Você irá à festa? (S/N): ")
        presencaInput = readlnOrNull()?.trim()?.uppercase() ?: ""
        if (presencaInput != "S" && presencaInput != "N") {
            println("Opção inválida! Digite 'S' para Sim ou 'N' para Não.")
        }
    } while (presencaInput != "S" && presencaInput != "N")
    convidado.presenca = presencaInput == "S"

    listaConvidados.add(convidado)
    println("Convidado cadastrado com sucesso!")
}

private fun listar() {
    if (listaConvidados.isEmpty()) {
        println("Lista vazia.")
        return
    }

    println("\n--- LISTA DE CONVIDADOS ---")
    listaConvidados.forEachIndexed { index, convidado ->
        println("[$index] Nome: ${convidado.nome}, Presente: ${convidado.presente}, Restrição: ${convidado.alimentar}, Presença: ${if (convidado.presenca) "Sim" else "Não"}")
    }
}

private fun editar() {
    if (listaConvidados.isEmpty()) {
        println("Lista vazia.")
        return
    }

    listar()
    var pos: Int
    do {
        print("Digite o número do convidado que deseja editar: ")
        pos = readlnOrNull()?.toIntOrNull() ?: -1
        if (pos !in listaConvidados.indices) {
            println("Posição inválida! Digite um índice válido.")
        }
    } while (pos !in listaConvidados.indices)

    val convidado = listaConvidados[pos]
    var campo: Int
    do {
        println("O que deseja editar?\n1 - Nome\n2 - Presente\n3 - Restrição Alimentar\n4 - Presença")
        campo = readlnOrNull()?.toIntOrNull() ?: -1
        if (campo !in 1..4) {
            println("Opção inválida! Digite um número de 1 a 4.")
        }
    } while (campo !in 1..4)

    when (campo) {
        1 -> {
            val regexNome = Regex("^[A-Za-zÀ-ÿ ]+$")
            var novoNome: String
            do {
                print("Novo nome: ")
                novoNome = readlnOrNull()?.trim() ?: ""
                if (!novoNome.matches(regexNome)) {
                    println("Nome inválido! Apenas letras e espaços são permitidos.")
                }
            } while (!novoNome.matches(regexNome))
            convidado.nome = novoNome
        }
        2 -> {
            print("Novo presente: ")
            convidado.presente = readlnOrNull()?.trim() ?: ""
        }
        3 -> {
            print("Nova restrição alimentar: ")
            convidado.alimentar = readlnOrNull()?.trim() ?: ""
        }
        4 -> {
            var presenca: String
            do {
                print("Presença (S/N): ")
                presenca = readlnOrNull()?.trim()?.uppercase() ?: ""
                if (presenca != "S" && presenca != "N") {
                    println("Opção inválida! Digite 'S' para Sim ou 'N' para Não.")
                }
            } while (presenca != "S" && presenca != "N")
            convidado.presenca = presenca == "S"
        }
    }
    println("Convidado atualizado com sucesso!")
}

private fun excluir() {
    if (listaConvidados.isEmpty()) {
        println("Lista vazia.")
        return
    }

    listar()
    var pos: Int
    do {
        print("Digite o número do convidado que deseja excluir: ")
        pos = readlnOrNull()?.toIntOrNull() ?: -1
        if (pos !in listaConvidados.indices) {
            println("Posição inválida! Digite um índice válido.")
        }
    } while (pos !in listaConvidados.indices)

    listaConvidados.removeAt(pos)
    println("Convidado excluído com sucesso.")
}

private fun buscar() {
    if (listaConvidados.isEmpty()) {
        println("Lista vazia.")
        return
    }

    val regex = Regex("^[A-Za-zÀ-ÿ ]+$")
    var termo: String
    do {
        print("Digite o nome que deseja buscar: ")
        termo = readlnOrNull()?.trim() ?: ""
        if (!termo.matches(regex)) {
            println("Termo inválido! Apenas letras e espaços são permitidos.")
        }
    } while (!termo.matches(regex))

    val resultados = listaConvidados.filter {
        it.nome.contains(termo, ignoreCase = true)
    }

    if (resultados.isEmpty()) {
        println("Nenhum convidado encontrado com o nome '$termo'.")
    } else {
        println("Convidados encontrados:")
        resultados.forEach {
            println("Nome: ${it.nome}")
        }
    }
}