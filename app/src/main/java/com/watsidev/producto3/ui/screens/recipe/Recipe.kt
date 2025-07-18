package com.watsidev.producto3.ui.screens.recipe

import com.watsidev.producto3.R

data class Recipe(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val videoRes: Int, // Si usarás video local
    val ingredients: List<String>,
    val preparation: String,
    val isFavorite: Boolean = false
)


val recipesList = listOf(
    Recipe(
        id = 1,
        name = "Arroz blanco",
        imageRes = R.drawable.arroz_blanco,
        videoRes = R.raw.arroz_blanco,
        ingredients = listOf("Arroz", "Agua", "Sal", "Aceite"),
        preparation = "Lava el arroz, ponlo a hervir con agua, sal y aceite.",
        isFavorite = false
    ),
    Recipe(
        id = 2,
        name = "Pollo al horno",
        imageRes = R.drawable.pollo_al_horno,
        videoRes = R.raw.pollo_al_horno,
        ingredients = listOf("Pollo", "Sal", "Pimienta", "Limón"),
        preparation = "Sazona el pollo y hornéalo por 45 minutos.",
        isFavorite = false
    ),
    Recipe(
        id = 3,
        name = "Ensalada César",
        imageRes = R.drawable.ensalada_cesar,
        videoRes = R.raw.ensalada_cesar,
        ingredients = listOf("Lechuga", "Pollo", "Queso", "Aderezo César"),
        preparation = "Mezcla todos los ingredientes y sirve frío.",
        isFavorite = false
    ),
    Recipe(
        id = 4,
        name = "Tacos de carne",
        imageRes = R.drawable.tacos_de_carne,
        videoRes = R.raw.tacos_de_carne,
        ingredients = listOf(
            "2 Cucharadas de aceite vegetal",
            "800 Gramos de bistec de res",
            "1 Cebolla fileteada",
            "3 Cucharadas de Jugo MAGGI®",
            "3 Cucharadas de Salsa Tipo Inglesa CROSSE & BLACKWELL®",
            "Cilantro desinfectado y picado finamente",
            "Tortillas de maíz calientes",
        ),
        preparation = "Cocina\n" +
                "1.En una sartén caliente agrega aceite, las tiras de bistec y la cebolla.\n" +
                "Sazona\n" +
                "2.Vierte el Jugo MAGGI® con la Salsa Tipo Inglesa CROSSE & BLACKWELL® y cocina hasta que doren por completo todos los ingredientes.\n" +
                "Prepara los tacos\n" +
                "3.Retira del sartén, sirve en las tortillas con el perejil. Ofrece y disfruta.",
        isFavorite = false
    ),
    Recipe(
        id = 5,
        name = "Sopa de verduras",
        imageRes = R.drawable.sopa_de_verduras,
        videoRes = R.raw.sopa_de_verduras,
        ingredients = listOf("Zanahoria", "Papa", "Apio", "Agua"),
        preparation = "Hierve las verduras en agua hasta que estén suaves.",
        isFavorite = false
    ),
    Recipe(
        id = 6,
        name = "Pizza margarita",
        imageRes = R.drawable.pizza_margarita,
        videoRes = R.raw.pizza_margarita,
        ingredients = listOf(
            "220 g. de masa de pizza italiana",
            "90 g. de queso mozzarella",
            "Hojas de albahaca fresca",
            "100 g. de salsa de tomate natural triturado",
            "Sal (al gusto)",
            "Una pizca de pimienta negra recién molida",
            "Aceite de oliva virgen extra"
        ),
        preparation = "Preparación de la masa de pizza\n" +
                "Si hemos optado por preparar una masa de pizza casera, en el blog podéis encontrar la receta de como la hacemos en casa. Se trata de una masa muy sabrosa y tierna que os animo a que preparéis.\n" +
                "Una vez que tenemos la masa de pizza preparada, lo primero que hacemos es encender el horno. A tope, lo máximo que de vuestro horno. En mi caso a 250º C durante unos 10 minutos y que esté caliente en el momento de introducir la pizza.\n" +
                "\n" +
                "Como la temperatura ha de ser alta, puede llevar bien unos 10 o 15 minutos. Será el tiempo recomendado que necesitemos para montar la pizza.\n" +
                "Ponemos un cuenco (apto para horno) con agua, para que tenga algo de humedad cuando vayamos a hornearla. Una vez que tenemos la masa de la pizza preparada, ya extendida en papel de horno, preparamos el relleno.\n" +
                "Preparación de la salsa de tomate para la pizza margarita\n" +
                "Si no tenemos tomate natural triturado lo podemos hacer casa de manera muy sencilla. Pelamos dos tomates y lo partimos a la mitad. Con ayuda de un rallador conseguiremos esa salsa natural que será la base de nuestra pizza.\n" +
                "Si elegimos rallar el tomate, tenemos que evitar un excesivo líquido, con lo que es mejor un filtrado para eliminar agua, cuanta más mejor.\n" +
                "Le añadimos un poquito de sal y pimienta negra recién molida. Partimos unas 3-4 hojas de albahaca fresca en la salsa de tomate con unas gotas de aceite de oliva virgen extra. Removemos para juntar todos los ingredientes.\n" +
                "Untamos con el tomate triturado por toda la superficie de la masa. Teniendo cuidado de dejar libre unos 2 cm. por todo el perímetro de la pizza. Podemos ayudarnos con una cuchara o cucharón, sino con las propias manos (siempre limpias).\n" +
                "Horneado de la pizza margarita\n" +
                "Cortamos el queso mozzarella en láminas gruesas y las repartimos por la superficie de la pizza. Sin cargarla demasiado, no debe de llevar mucha cantidad.\n" +
                "Llevamos la pizza entre dos personas la pizza entre dos personas por las esquinas del papel de horno con cuidado. Abrimos el horno y la ponemos en la bandeja del medio que estará muy caliente.\n" +
                "Horneamos con el horno bien caliente a 250º C calor arriba-abajo y aire durante 7-8 minutos. Es tiempo suficiente para que la masa esté lista y el queso fundido. Distribuimos unas hojitas de albahaca fresca por encima y regamos con un pequeño hilo de aceite de oliva virgen extra. Lista para disfrutar de la madre de todas las pizzas.",
        isFavorite = false
    ),
    Recipe(
        id = 7,
        name = "Pasta boloñesa",
        imageRes = R.drawable.pasta_bolonesa,
        videoRes = R.raw.pasta_bolones,
        ingredients = listOf("Pasta", "Carne molida", "Tomate", "Queso"),
        preparation = "Cocina la pasta y la salsa boloñesa, mezcla y sirve.",
        isFavorite = false
    ),
    Recipe(
        id = 8,
        name = "Hamburguesa clásica",
        imageRes = R.drawable.hamburguesa_clasica,
        videoRes = R.raw.hamburguesa,
        ingredients = listOf("Pan", "Carne", "Lechuga", "Tomate"),
        preparation = "Arma la hamburguesa y cocina la carne al gusto.",
        isFavorite = false
    ),
    Recipe(
        id = 9,
        name = "Ceviche de pescado",
        imageRes = R.drawable.ceviche_de_pescado,
        videoRes = R.raw.ceviche_de_pescado,
        ingredients = listOf("Pescado", "Limón", "Cebolla", "Cilantro"),
        preparation = "Marina el pescado en limón y mezcla con los demás ingredientes.",
        isFavorite = false
    ),
)