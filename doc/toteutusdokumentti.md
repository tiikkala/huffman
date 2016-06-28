## Johdanto

Projektin aiheena oli toteuttaa tiedoston pakkaus ja purku käyttäen Huffman-koodausta. Kaikki ohjelmassa käytetyt tietorakenteet ja algoritmit on toteutettu itse.

## Käytety teknologiat

- Java
- Apache Maven
- Javadoc
- Markdown
- Git

## Ohjelman rakenne

- 'ikkala

## O-analyysit

Huffman-koodin kirjoittamisen aikavaativuus on O(*k* log *k*), missä *k* on tiedoston eri merkkien lukumäärä (tekstitiedostoissa *k* <= 256). Kun koodi on selvillä, käydään tiedosto läpi merkki merkiltä siten, että pakattuun tiedostoon kirjoitetaan merkkiä kuvaava binäärikoodi. Tämän operaation aikavaativuus on O(*n*), missä *n* on tiedoston kaikkien merkkien lukumäärä.

Purettaessa koodi rekonstruoidaan lukeamalla tiedoston alusta koodien pituudet sekä tiedostossa käytettävät merkit ((O(*k*)). Kun koodi on selvillä, luetaan tiedostoa bitti kerrallaan ja kirjoitetaan luettuja koodeja vastaavat merkit uudeen tiedostoon O(*n*).

Omassa toetutuksessani purkaminen on pakkaamista hitaampaa, sillä jokaisen luetun bitin jälkeen verrataan saatua koodia taulukkomuotoiseen, pahimmillaan 256:n pituiseen koodikirjaan etsien sieltä vastaavuutta. Operaatiota voisi tehostaa esimerkiksi tallentamalla koodit hajautustauluun.


