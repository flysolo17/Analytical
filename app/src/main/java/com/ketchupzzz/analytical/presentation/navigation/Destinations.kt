package com.ketchupzzz.analytical.presentation.navigation



interface Destinations  {
   val route: String
}


object Root : Destinations {
    override val route = "root"
}

object Auth : Destinations {
    override val route = "auth"
}
object Quiz : Destinations {
    override val route: String
        get() = "quiz"
}


object Login : Destinations {
    override val route: String
        get() = "login"
}

object Register : Destinations {
    override val route: String
        get() = "register"
}

object Main : Destinations {
    override val route: String
        get() = "main"
}

object ViewQuiz : Destinations {
    override val route: String
        get() = "view-quiz"
}

object Gaming : Destinations {
    override val route: String
        get() = "gaming"
}
