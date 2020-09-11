#Encryption-Decryption

Ce programme fonctionne avec des arguments de ligne de commandes.

Si vous utiliser l'IDE Intellij : 

fenêtre Run -> Run... -> Edit Configurations -> Program arguments -> Run

Exemples d'arguments :

    -mode [enc/dec]
    -data "une phrase"
    -in monFichierSource.txt
    -out monFichierTansforme.txt
    -alg unicode (non requis -> alg shift en cas d'absence)
    -key int
    
-data OU -in

Exemples de commandes :

    -mode enc -data "je fais une phrase" -key 5
    -mode dec -data "oj kfnx zsj umwfxj" -key 5
    -mode enc -in test1.txt -out outTest1.txt -alg unicode -key 5
    -mode dec -in test2.txt -out outTest2.txt -alg unicode -key 5
    
