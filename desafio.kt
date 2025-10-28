// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String, val email: String) {
    override fun toString(): String = "Usuario(nome='$nome', email='$email')"
}

data class ConteudoEducacional(var nome: String, val nivel: Nivel, val duracao: Int = 60) {
    override fun toString(): String = "$nome (${nivel.name.lowercase()}) - ${duracao}min"
}

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        if (usuario !in inscritos) {
            inscritos.add(usuario)
            println("✅ $usuario matriculado com sucesso na formação '$nome'")
        } else {
            println("⚠️  $usuario já está matriculado na formação '$nome'")
        }
    }
    
    fun cancelarMatricula(usuario: Usuario) {
        if (inscritos.remove(usuario)) {
            println("❌ Matrícula de $usuario cancelada na formação '$nome'")
        } else {
            println("⚠️  $usuario não encontrado na formação '$nome'")
        }
    }
    
    fun listarInscritos() {
        println("\n📋 Alunos matriculados na formação '$nome':")
        if (inscritos.isEmpty()) {
            println("   Nenhum aluno matriculado")
        } else {
            inscritos.forEachIndexed { index, usuario ->
                println("   ${index + 1}. $usuario")
            }
        }
    }
    
    fun calcularDuracaoTotal(): Int {
        return conteudos.sumOf { it.duracao }
    }
    
    override fun toString(): String {
        return """
            🎓 Formação: $nome
            📚 Conteúdos (${conteudos.size}):
            ${conteudos.joinToString("\n") { "   • $it" }}
            ⏰ Duração total: ${calcularDuracaoTotal()} minutos
            👥 Alunos matriculados: ${inscritos.size}
        """.trimIndent()
    }
}

fun main() {
    println("🚀 DIO - Sistema de Formações\n")
    
    // Criando conteúdos educacionais
    val conteudosKotlin = listOf(
        ConteudoEducacional("Introdução ao Kotlin", Nivel.BASICO, 90),
        ConteudoEducacional("Funções e Lambdas", Nivel.INTERMEDIARIO, 120),
        ConteudoEducacional("Programação Assíncrona", Nivel.DIFICIL, 180)
    )
    
    val conteudosAndroid = listOf(
        ConteudoEducacional("UI com Jetpack Compose", Nivel.INTERMEDIARIO, 150),
        ConteudoEducacional("Arquitetura MVVM", Nivel.DIFICIL, 120),
        ConteudoEducacional("Testes no Android", Nivel.INTERMEDIARIO, 90)
    )
    
    // Criando formações
    val formacaoKotlin = Formacao("Kotlin Developer", conteudosKotlin)
    val formacaoAndroid = Formacao("Android Developer", conteudosAndroid)
    
    // Criando usuários
    val aluno1 = Usuario("João Silva", "joao@email.com")
    val aluno2 = Usuario("Maria Santos", "maria@email.com")
    val aluno3 = Usuario("Pedro Costa", "pedro@email.com")
    
    // Simulando cenários de matrícula
    println("=== MATRÍCULAS ===")
    formacaoKotlin.matricular(aluno1)
    formacaoKotlin.matricular(aluno2)
    formacaoKotlin.matricular(aluno1) // Tentativa de matrícula duplicada
    
    formacaoAndroid.matricular(aluno2)
    formacaoAndroid.matricular(aluno3)
    
    // Listando informações
    println("\n=== INFORMAÇÕES DAS FORMAÇÕES ===")
    println(formacaoKotlin)
    println("\n" + "=".repeat(50) + "\n")
    println(formacaoAndroid)
    
    // Listando alunos matriculados
    formacaoKotlin.listarInscritos()
    formacaoAndroid.listarInscritos()
    
    // Simulando cancelamento de matrícula
    println("\n=== CANCELAMENTO DE MATRÍCULA ===")
    formacaoKotlin.cancelarMatricula(aluno1)
    formacaoKotlin.cancelarMatricula(aluno3) // Aluno não matriculado
    
    // Status final
    println("\n=== STATUS FINAL ===")
    formacaoKotlin.listarInscritos()
    
    // Demonstrando funcionalidades adicionais
    println("\n=== ESTATÍSTICAS ===")
    println("📊 Duração total da formação Kotlin: ${formacaoKotlin.calcularDuracaoTotal()} minutos")
    println("📊 Duração total da formação Android: ${formacaoAndroid.calcularDuracaoTotal()} minutos")
    
    val totalAlunos = formacaoKotlin.inscritos.size + formacaoAndroid.inscritos.size
    println("👥 Total de alunos no sistema: $totalAlunos")
}