package ca.nkrishnaswamy.virtualcardcollectionbinder

object UserPokemonCardCollection {
    private var pokemonCardCollection: ArrayList<PokemonCard> = arrayListOf<PokemonCard>()  //singleton

    fun getPokemonCollection(): ArrayList<PokemonCard>{
        return pokemonCardCollection
    }
    fun pokemonCollectionAdder(name: String, hp: String){      //parameters can be changed when UI is actually implemented to other Pokemon Card attributes
        for (page in PokemonCardFullDb.getPokemonDb()){    //returns each page of cards from url
            for (card in page.getPokemonCardPages()){         //returns each card within current page
                if ((card.getName().contains(name, ignoreCase=true)) && (card.gethp()==hp)){      //parameters can be changed when UI is actually implemented to other Pokemon Card attributes
                    pokemonCardCollection.add(card)
                }
            }
        }
    }
}