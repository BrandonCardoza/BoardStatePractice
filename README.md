# Board State Practice

Author: Brandon Cardoza

## Overview

Runs a GUi program that generates a random board state for the trading card game Magic the Gathering. User can input up to four decks
and switch views between each of the four decks. Health of all 4 players is shown at the top of the window.

The four decks that can be inputted are as follows (CASE SENSITIVE):
Tiamat
Niv-Mizzet Parun
Yuriko the Tiger's Shadow
Erza Scarlet Titania

To add a deck to the GUI, in the text box on the right, enter one of the above deck names, click the "Add Deck" button followed by the "Add Cards" button.
To refresh and create a new board state, click the "Refresh" button in the bottom right of the window.
To switch deck views, use the dropdown menu in the top-right of the window.

## Compiling and Using

To compile, execute the following command in the main project directory:

$   javac *.java

Run the compiled program with the following command:

$   java BoardStatePracticeGUI
