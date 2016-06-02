# Määrittelydokumentti

## Työn aihe

Toteutan **Huffmanin koodausalgoritmin** ohjelmaan jolla pakataan ja puretaan tiedostoja. Algoritmissä käytetyt tietorakenteet ovat **binääripuu** ja **keko**. Teen ohjelman Java-kielellä.

Huffmanin algoritmissa pakattavan tiedoston merkit järjestetään yleisyysjärjestykseen, antaen yleisimälle merkille lyhyin koodi. Tästä seuraa, että harvinaiset merkit saavat pidemmät koodit, mutta kunhan yleisten ja harvinaisten merkkien määrässä on riittävän suuri ero, pienenee tiedoston koko huomattavasti. Huffman-koodi antaa jokaiselle merkille niin sanotun etuliitekoodin (prefix code tai prefix-free code). Etuliitekoodissa minkään merkin koodi ei voi olla toisen merkin koodin alkuosa, joten koodi on aina yksiselitteinen, vaikka merkkien koodien pituudet vaihtelevatkin.

## Ohjelman käyttö

Ohjelma saa syötteenä polun pakattavaan tai purettavaan tiedostoon. Käyttäjä valitsee, pakataanko vai puretaanko.

## Aika- ja tilavaativuudet

Huffmanin algoritmin aikavaativuus on O(*k* log *k*), missä *k* on tiedoston eri merkkien lukumäärä (tekstitiedostoissa *k* <= 256). Pakkaamiseen ja purkamiseen kuluvaa aikaa hallitsee kuitenkin tiedoston lukeminen ja kirjoittaminen, joiden aikavaativuus on O(*n*), missä *n* on tiedoston sisältämien merkkien lukumäärä.

## Lähteet 

Cormen et al. "Introduction to Algorithms"

https://en.wikipedia.org/wiki/Huffman_coding
