# 7Physics

Équipe 3: Noa Ammirati, Fanny Delnondedieu, Quentin Gendarme, Pierre Lotte, Théo Pirouelle et Éléa Turc

<img src="https://avatars.githubusercontent.com/u/83647846?s=200&v=4" alt="cube" style="zoom:40%;" />

> [Ce site](http://lo-th.github.io/Oimo.js/#basic) donne une réalisation avancée de l'idée de ce projet. L'idée de ce projet est de réaliser un moteur 3D permettant de réaliser des simulations de notions de physique élémentaires telles que la gravité, les collisions etc.



**Pour** toutes les personnes **qui souhaitent** simuler la physique réelle ou modéliser en 3D.
**Notre produit est** un moteur 3D **qui** simule la physique du monde réel ou bien une physique définie par l'utilisateur.
**A la différence des** autres moteurs physiques, le notre **permet de** modéliser et simuler simplement.

---


## Table des matières
- [Description](#description)
- [Diagramme UML](#diagramme-uml)
- [Installation](#installation)
  * [Git](#git)
  * [Projet](#projet)



---

## Description

L'idée de ce projet est de réaliser un moteur 3D permettant de réaliser des simulations de notions de physique élémentaires telles que la gravité, les collisions, etc.

Ce projet pourrait alors se séparer en 2 objectifs principaux qui sont aussi les 2 briques principales nécessaire à sa réalisation :

1. Créer une bibliothèque de rendu des formes tri-dimensionnelles basiques telles que des cubes, sphères, pyramides, etc. Tout cela avec possibilité de changer le point de vue de l'utilisateur en se déplaçant dans l'espace (concept de caméra à la première personne). Ajouté à cela, il est possible de créer plusieurs fonctionnalités visuelles telles que la présence d'ombre et de lumière, de texture, etc. Afin de créer des objets 3D réalistes.
2. Créer un moteur physique responsable de la simulation des concepts évoqués plus tôt (gravité, collisions, etc.). Tous ces concepts pourront alors être manipulés à souhait grâce à des notions de vélocité, de poids, etc. Qui sont autant de paramètre influant sur ces phénomènes physiques.

Une fois ces objectifs remplis, nous pourrions créer une interface graphique simple permettant de visualiser des simulations prédéfinies voire même donner à l'utilisateur la possibilité de créer des simulations de toute pièce.

Puisque le moteur physique se voudra une simulation aussi proche de la réalité que possible, nous pourrons afficher et fournir des données précises quant aux forces mises en jeu, vélocité des objets, etc. Il serait alors possible de faire un rendu dynamique des objets en fonctions de ces contraintes.



Découpage du projet avec un répertoire principal et deux sous-répertoires :

- *7Physics*
  - *Engine* : moteur physique
  - *Renderer* : moteur de rendu



## Diagramme UML

![UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/7Physics/7Physics/master/class.plantuml)



___

## Installation

### Git

Cloner le projet avec les sous-modules :

```sh
git clone --recursive https://github.com/7Physics/7Physics.git
```



### Projet

Procédure à suivre si OpenGL ne fonctionne pas (PC de l'école) :

> - Supprimer java de son système : `sudo apt list --installed | grep jdk`
>   - `sudo apt remove` (des résultats de la commande précèdente (ex: `sudo apt remove openjdk-11-jdk`))
> - Installer java8 : `sudo apt install openjdk-8-jdk`
> - Vérifier avec la commande : `java -version`

Procédure installation Gradle v7.0 (PC de l'école) :

> - Télécharger le zip complet (v7.0): https://gradle.org/releases/
> - Dezipper : `unzip gradle-7.0-bin.zip`
> - Placer le dossier obtenu dans `/opt/gradle/`
> - Ouvrir le fichier `.profile` et ajouter : `export PATH="/opt/gradle/gradle-7.0/bin:$PATH"`
> - Mettre à jour avec la commande : `source .profile`
> - Vérifier l'installation de Gradle : `gradle -v`
>
> 
>
> A noter qu'il est possible de se passer de cette étape en utilisant le *wrapper gradle* (*gradlew*) contenu dans chaque projet *Gradle*.
>
> - Pour build : `.\gradlew build`
> - Pour run : `.\gradlew run`
>
> En cas d'erreur `/usr/bin/env: 'sh\r': No such file or directory` il faut utiliser `dos2unix` (à cause des retours chariots de Windows).

Utilisation de Gradle :

> - Dans le repo git clôné (7Physics/) :
>   - `gradle build` : permet de "*builder*" le projet (compilation + import des librairies dans le projet)
>   - `gradle run` : permet de lancer l'application
