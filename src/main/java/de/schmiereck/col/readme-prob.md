
 0 1 2 3 4 5
| |R| | |L| |
 =>
| | |R|L| | |
 =>
| | |L|R| | |
 =>
 | |L| | |R| |

Problem:
 0 1 2 3 4
| |R| |L| |
=>
| | |?| | |
L und R spüren sich noch nicht,
können aber nicht beide 2 besetzen.

Lösung:
 0 A 2 B 4 5
| |R| |L| | |  E-Feld
|l| |r| | | |  e-Feld A
| | |l| |r| |  e-Feld B
| |l|P|r| | |  p-Feld
=>
| |L| | |R| |  E-Feld
|l| |r|l| |r|  e-Feld

 0 A 2 3 B 5
| |R| | |L| |  E-Feld
|l| |r|r| | |  e-Feld A
| | |l|l| |r|  e-Feld B
| |l|P|r|r| |  p-Feld 
| |l|l|P|r| |  p-Feld
=>
| |L| | |R| |  E-Feld
|l| |r|l| |r|  e-Feld

 0 A 2 3 B 5
| |R| | | |L| |  E-Feld
|l| |r|r| | | |  e-Feld A
| | | |l|l| |r|  e-Feld B
| |l|l|P|r|r| |  p-Feld

e-Feld entsteht in der Nachbarschaft eines E-Feld Teilchens.
Es hat eine Richtung.
Es hat eine Stärke die der Wahrscheinlichkeit des E-Felds entspricht sich in diese 
Richtung zu bewegen (Impuls).

p-Feld entsteht in der Nachbarschaft eines e-Feld Teilchens.
p-Feld überträgt den Impuls.

# Field and Probability
Immer in beide Richtungen volle Stärke.
```
        L   S   R
        0   90  10
e L:100             R:100

        L   S   R
        0   30  70
e L:100             R:100

        L   S   R
        0   100 0
e L:100             R:100

```