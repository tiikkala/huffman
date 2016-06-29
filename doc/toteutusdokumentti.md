## Johdanto

Projektin aiheena oli toteuttaa tiedoston pakkaus ja purku käyttäen Huffman-koodausta. Kaikki ohjelmassa käytetyt tietorakenteet ja algoritmit on toteutettu itse.

## Käytety teknologiat

- Java
- Apache Maven
- Javadoc
- Markdown
- Git

## Ohjelman rakenne

- 'ikkala.huffmancompress' sisältää ohjelman pääluokan, pakkaamisen ja purkamisen toteuttavat luokat sekä diagnostiikan
- 'ikkala.huffmancompress.datastructures' sisältää ohjelmassa käytettävät tietorakenteet
- 'ikkala.huffmancompress.huffmantree' sisältää Huffmanin algoritmin toteutuksen sekä enkoodaamisen ja dekoodaamisen
- 'ikkala.huffmancompress.io' sisältää tiedoston kirjoittamiseen ja lukemiseen liittyvät luokat

## Käytetyt tietorakenteet

- Minimikeko
- Solmu, lehti

## Pakkaus

1. Käydään syötetiedosto läpi kerran ja lasketaan sen symbolien (*n*) esiintymiskerrat, aikavaativuus O(*n*)).

2. Luodaan Huffman-koodipuu, aikavaativuus O(log *k*), missä k on tiedoston eri symbolien lukumäärä.

3. Luodaan kanoninen koodaus Huffman puusta, aikavaativuus O(log *k*)

4. Kun koodi on selvillä, käydään tiedosto läpi merkki merkiltä siten, että pakattuun tiedostoon kirjoitetaan merkkiä kuvaava binäärikoodi. Aikavaativuus O(*n*).

## Purku

1. Rekonstruoidaan kanoninen koodi lukeamalla tiedoston alusta koodien pituudet sekä tiedostossa käytettävät merkit (O(*k*). 

2. Kun koodi on selvillä, luetaan tiedostoa bitti kerrallaan ja kirjoitetaan luettuja koodeja vastaavat merkit uudeen tiedostoon O(*n*). Jokaisen luetun bitin jälkeen haetaan koodille vastaavuutta hajautustaulusta.

## Paranneltavaa

Koodia voisi optimoida useammastakin kohtaa. Pahin pullonkaula on tällä hetkellä purkaminen, joka nopeutuisi nykyisestä luultavasti jo pelkästään sillä, että kanonisoiduista koodista rakentaisi Huffman-puun, jota pitkin kulkemalla koodin saisi purettua ilman, että jokaisen bitin jälkeen täytyy koodia verrata hajautustaulun avaimiin.
