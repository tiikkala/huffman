# Viikkoraportti 3

Toteutettuani viime viikolla algoritmin ytimen, lähdin miettimään, millä tavalla tieto koodauksesta kannattaa tallentaa pakattuun tiedostoon tiedoston purkamista varten. Yksi tapa olisi liittää tiedostoon mukaan koko koodipuu, mutta tähän on tehokkaampiakin tapoja. Päätin optimoida ohjelmaani toteuttamalla kanonisoidun koodauksen, jossa alkuperäisestä Huffmanin puusta saadut koodit muutetaan sellaiseen muotoon, että pakatun tiedoston purkamiseen tarvitaan vai tieto koodien pituuksista. Koodien pituuksien avulla on mahdollista rekonstruoida kanonisoitu Huffman-puu, josta varsinaiset dekoodauksessa käytetyt koodit saa selville. 

Tällä viikolla minulla meni eniten aikaa tiedonhakuun, jotta löytäisin jonkin selkeän kuvauksen koodien kanonisointialgoritmille. Kun lopulta löysin tällaisen, ei algoritmin toteuttaminen ollut erityisen hankalaa.

Seuraavalla viikolla aloitan siitä, että kirjoitan algoritmin, joka rekonstruoi kanonisoidun Huffman-puun koodien pituuksista. Tämän jälkeen rupean toteuttamaan tiedostojen lukemisesta ja kirjoittamisesta vastaavia luokkia.
