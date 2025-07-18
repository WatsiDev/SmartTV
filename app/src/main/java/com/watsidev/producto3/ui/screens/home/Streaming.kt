package com.watsidev.producto3.ui.screens.home

import com.watsidev.producto3.R

data class Streaming (
    val id: Int,
    val image: Int,
    val name: String,
    val section: Section
)

enum class Section(val displayName: String) {
    SEGUIR_VIENDO("Seguir viendo"),
    POPULARES("Populares"),
    NUEVOS_LANZAMIENTOS("Nuevos lanzamientos"),
    RECOMENDADOS("Recomendados")
}



val streamingList = listOf(
    // Seguir viendo (Harry Potter movies)
    Streaming(
        id = 1,
        image = R.drawable.harry_potter_1,
        name = "Harry Potter y la piedra filosofal",
        section = Section.SEGUIR_VIENDO
    ),
    Streaming(
        id = 2,
        image = R.drawable.harry_potter_2,
        name = "Harry Potter y la cámara secreta",
        section = Section.SEGUIR_VIENDO
    ),
    Streaming(
        id = 3,
        image = R.drawable.harry_potter_3,
        name = "Harry Potter y el prisionero de Azkaban",
        section = Section.SEGUIR_VIENDO
    ),
    Streaming(
        id = 4,
        image = R.drawable.harry_potter_4,
        name = "Harry Potter y el cáliz de fuego",
        section = Section.SEGUIR_VIENDO
    ),
    Streaming(
        id = 5,
        image = R.drawable.harry_potter_5,
        name = "Harry Potter y la Orden del Fénix",
        section = Section.SEGUIR_VIENDO
    ),
    Streaming(
        id = 6,
        image = R.drawable.harry_potter_6,
        name = "Harry Potter y el misterio del príncipe",
        section = Section.SEGUIR_VIENDO
    ),
    Streaming(
        id = 7,
        image = R.drawable.harry_potter_7,
        name = "Harry Potter y las Reliquias de la Muerte - Parte 1",
        section = Section.SEGUIR_VIENDO
    ),
    Streaming(
        id = 8,
        image = R.drawable.harry_potter_8,
        name = "Harry Potter y las Reliquias de la Muerte - Parte 2",
        section = Section.SEGUIR_VIENDO
    ),
    // Populares
    Streaming(
        id = 9,
        image = R.drawable.breaking_bad,
        name = "Breaking Bad",
        section = Section.POPULARES
    ),
    Streaming(
        id = 10,
        image = R.drawable.stranger_things,
        name = "Stranger Things",
        section = Section.POPULARES
    ),
    Streaming(
        id = 11,
        image = R.drawable.betty_lafea,
        name = "Betty la fea",
        section = Section.POPULARES
    ),
    Streaming(
        id = 12,
        image = R.drawable.la_casa_de_papel,
        name = "La Casa de Papel",
        section = Section.POPULARES
    ),
    Streaming(
        id = 13,
        image = R.drawable.game_of_thrones,
        name = "Game of Thrones",
        section = Section.POPULARES
    ),
    Streaming(
        id = 14,
        image = R.drawable.friends,
        name = "Friends",
        section = Section.POPULARES
    ),
    Streaming(
        id = 15,
        image = R.drawable.the_office,
        name = "The Office",
        section = Section.POPULARES
    ),
    Streaming(
        id = 16,
        image = R.drawable.squid_game,
        name = "Squid Game",
        section = Section.POPULARES
    ),
    Streaming(
        id = 17,
        image = R.drawable.lost,
        name = "Lost",
        section = Section.POPULARES
    ),
    // Nuevos lanzamientos
    Streaming(
        id = 18,
        image = R.drawable.oppenheimer,
        name = "Oppenheimer",
        section = Section.NUEVOS_LANZAMIENTOS
    ),
    Streaming(
        id = 19,
        image = R.drawable.dune,
        name = "Dune: Parte Dos",
        section = Section.NUEVOS_LANZAMIENTOS
    ),
    Streaming(
        id = 20,
        image = R.drawable.barbie,
        name = "Barbie",
        section = Section.NUEVOS_LANZAMIENTOS
    ),
    Streaming(
        id = 21,
        image = R.drawable.spiderman_across_spiderverse,
        name = "Spider-Man: Across the Spider-Verse",
        section = Section.NUEVOS_LANZAMIENTOS
    ),
    Streaming(
        id = 22,
        image = R.drawable.guardians_galaxy_3,
        name = "Guardianes de la Galaxia Vol. 3",
        section = Section.NUEVOS_LANZAMIENTOS
    ),
    Streaming(
        id = 23,
        image = R.drawable.mission_impossible_7,
        name = "Misión Imposible: Sentencia Mortal",
        section = Section.NUEVOS_LANZAMIENTOS
    ),
    Streaming(
        id = 24,
        image = R.drawable.elemental,
        name = "Elemental",
        section = Section.NUEVOS_LANZAMIENTOS
    ),
    Streaming(
        id = 25,
        image = R.drawable.the_marvels,
        name = "The Marvels",
        section = Section.NUEVOS_LANZAMIENTOS
    ),
    Streaming(
        id = 26,
        image = R.drawable.napoleon,
        name = "Napoleón",
        section = Section.NUEVOS_LANZAMIENTOS
    ),
    // Recomendados
    Streaming(
        id = 27,
        image = R.drawable.dark,
        name = "Dark",
        section = Section.RECOMENDADOS
    ),
    Streaming(
        id = 28,
        image = R.drawable.interstellar,
        name = "Interstellar",
        section = Section.RECOMENDADOS
    ),
    Streaming(
        id = 29,
        image = R.drawable.chernobyl,
        name = "Chernobyl",
        section = Section.RECOMENDADOS
    ),
    Streaming(
        id = 30,
        image = R.drawable.black_mirror,
        name = "Black Mirror",
        section = Section.RECOMENDADOS
    ),
    Streaming(
        id = 31,
        image = R.drawable.house_of_cards,
        name = "House of Cards",
        section = Section.RECOMENDADOS
    ),
    Streaming(
        id = 32,
        image = R.drawable.mindhunter,
        name = "Mindhunter",
        section = Section.RECOMENDADOS
    ),
    Streaming(
        id = 33,
        image = R.drawable.sherlock,
        name = "Sherlock",
        section = Section.RECOMENDADOS
    ),
    Streaming(
        id = 34,
        image = R.drawable.true_detective,
        name = "True Detective",
        section = Section.RECOMENDADOS
    ),
    Streaming(
        id = 35,
        image = R.drawable.the_crown,
        name = "The Crown",
        section = Section.RECOMENDADOS
    ),

    Streaming(
        id = 36,
        image = R.drawable.the_bing_bang_theory,
        name = "The Big Bang Theory",
        section = Section.SEGUIR_VIENDO
    )
)