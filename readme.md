
```
3.  11  1.  11
..  1.  .2  1.

11
..

1.  .1
.1  1.

---------------------
8.. 61. 41. 211 
... 1.. 12. 12.
... ... ... 1..


                          -- -- --       gen:a
                             32          a1
                          -- -- --
                             32
                          -- -- --       gen:b
                          16  .          b1.1
                              . 16       b1.2
                       -- -- -- -- --
                          16    16
                       -- -- -- -- --    gen:c
                              8          b1.1
                        4  .             c1.1.1       
                           .  4          c1.1.2       
                              8          b1.2
                              4  .       c1.2.1
                                 .  4    c1.2.2
                    -- -- -- -- -- -- --
                        4    24     4
                    -- -- -- -- -- -- -- gen:d
                           4  .          d1.1.1     b1.1
                              .  4       d1.1.2     b1.1
                           4  .          d1.2.1     b1.2
                              .  4       d1.2.2     b1.2
                           2             c1.1.1        
                     1  .                d1.1.1.1    c1.1.1
                        .  1             d1.1.1.2    c1.1.1
                           2             c1.1.2        
                           1  .          d1.1.2.1    c1.1.2
                              .  1       d1.1.2.2    c1.1.2
                                 2       c1.2.1      
                           1  .          d1.2.1.1    c1.2.1
                              .  1       d1.2.1.2    c1.2.1
                                 2       c1.2.2      
                                 1  .    d1.2.2.1    c1.2.2
                                    .  1 d1.2.2.2    c1.2.2
                    -- -- -- -- -- -- --
                     1    15    15     1
                    -- -- -- -- -- -- -- gen:e
                              2          d1.1.1
                        1  .             e1.1.1.1   d1.1.1
                           .  1          e1.1.1.2   d1.1.1
                              2          d1.1.2
                              1  .       e1.1.2.1   d1.1.2
                                 .  1    e1.1.2.2   d1.1.2
                              2          d1.2.1
                        1  .             e1.2.1.1   d1.2.1
                           .  1          e1.2.1.2   d1.2.1
                              2          d1.2.2
                              1  .       e1.2.2.1   d1.2.2
                                 .  1    e1.2.2.2   d1.2.2
                        1  .             e1.1.1.1   d1.1.1
                           .  1          e1.1.1.2   d1.1.1
                           1             d1.1.1.1   
                           1             d1.1.1.2
                        1  .             e1.1.2.1   d1.1.2
                           .  1          e1.1.2.2   d1.1.2
                           1             d1.1.2.1
                           1             d1.1.2.2
                              1  .       e1.2.1.1   d1.2.1
                                 .  1    e1.2.1.2   d1.2.1
                                 1       d1.2.1.1
                                 1       d1.2.1.2
                              1  .       e1.2.2.1   d1.2.2
                                 .  1    e1.2.2.2   d1.2.2
                                 1       d1.2.2.1
                                 1       d1.2.2.2
                    -- -- -- -- -- -- --
                        4  4 16  4  4
                    -- -- -- -- -- -- --
                    
                    -- -- -- -- -- -- -- gen:e
                              8          d1.1.1 + d1.1.2 + d1.2.1 + d1.2.2
                           4             d1.1.1.1 + d1.1.1.2 + d1.1.2.1 + d1.1.2.2
                                 4       d1.2.1.1 + d1.2.1.2 + d1.2.2.1 + d1.2.2.2
                        1  .             e1.1.1.1   d1.1.1
                        1  .             e1.2.1.1   d1.2.1
                        1  .             e1.1.1.1   d1.1.1
                        1  .             e1.1.2.1   d1.1.2
                           .  1          e1.1.1.2   d1.1.1
                           .  1          e1.2.1.2   d1.2.1
                           .  1          e1.1.1.2   d1.1.1
                           .  1          e1.1.2.2   d1.1.2
                              1  .       e1.1.2.1   d1.1.2
                              1  .       e1.2.2.1   d1.2.2
                              1  .       e1.2.1.1   d1.2.1
                              1  .       e1.2.2.1   d1.2.2
                                 .  1    e1.1.2.2   d1.1.2
                                 .  1    e1.2.2.2   d1.2.2
                                 .  1    e1.2.1.2   d1.2.1
                                 .  1    e1.2.2.2   d1.2.2
                    -- -- -- -- -- -- --
                           

```
# Meta-Zustandsautomat
Der Zustand einer Zelle ist der Zustand eines Automaten mit einem Eingang/ Ausgang.
Er kann folgende Zustände annehmen: -1, 0, 1

Wenn ein Automat den Zustand einer anderen Zelle als Eingang benutzt, 
muss er auch deren Ausgangszustand bestimmen.

Eingangs-Zustände sind Zustand einer Zelle .
Ausgang ist der nächste Zustand des 

Wenn ein Ausgang von mehreren Engines gesetzt wird,
überlagern sich die Zustände.
=> Wie werden die aufgelöst, wenn es um die Ermittlung eines Eingangswertes geht?

```
a b c d e f a'       State 0
- - - - - - .   
+                   a 
  +                 b 
    +               c 
      +             d 
        +           e 
          +         f 
a b c d e f a'       State 1
- - - - - - .   
+++                 a+b
  +++               b+c
    +++             c+d
      +++           d+e
        +++         e+f
+         ++.       f+a
a b c d e f a'       State 2
===       ==...     fa+ab
=====               ab+bc
  =====             bc+cd
    =====           cd+de
      =====         de+ef
=       ====.       ef+fa
a b c d e f a'       State 3
#####     ##.....   fa+ab + ab+bc
#######             ab+bc + bc+cd
  #######           bc+cd + cd+de
    #######         cd+de + de+ef
#     ######.       de+ef + ef+fa
###     ####...     ef+fa + fa+ab

```

## State 0:
```
    0   =>   0
    1   =>   1
   -1   =>  -1
```

## State 1:
```
    0   0   =>   0   0     0   0
    0   1   =>   0   1     1   0
    0  -1  =>    0  -1    -1   0
    1   0   =>   1   0     0   1
    1   1   =>   1   1     1   1
    1  -1  =>    0   0     0   0       -1    1
   -1   0   =>  -1   0     0  -1
   -1   1   =>   0   0     0   0        1   -1
   -1  -1  =>   -1  -1    -1  -1
   ----------------------------
    0   0        0   0     0   0
```

## State 2 (static):
```
 0   0   0   0   =>  0   0   0     0   0   0     
 1   0   0   1   =>  0   0   1     0   1   0     
 2   0   0  -1  =>   0   0  -1     0  -1   0     
 3   0   1   0   =>  0   1   0     0   1   0     
 4   0   1   1   =>  0   1   1     1   1   0     
 5   0   1  -1  =>   0   0   0     0   0   0     
 6   0  -1   0   =>  0  -1   0     0  -1   0     
 7   0  -1   1   =>  0   0   0     0   0   0     
 8   0  -1  -1  =>   0  -1  -1    -1  -1   0     
    
 9   1   0   0   =>  1   0   0     0   1   0     
10   1   0   1   =>  1   0   1     1   0   1     
11   1   0  -1  =>   1   0  -1     0   0   0     
12   1   1   0   =>  1   1   0     0   1   1     
13   1   1   1   =>  1   1   1     1   1   1     
14   1   1  -1  =>   1   0   0     1   0   0     
15   1  -1   0   =>  0   0   0     0   0   0     
16   1  -1   1   =>  1  -1   1     0   1   0     
17   1  -1  -1  =>   0   0  -1     0   0  -1     
    
18  -1   0   0   => -1   0   0     0  -1   0     
19  -1   0   1   => -1   0   1     0   0   0     
20  -1   0  -1  =>  -1   0  -1    -1   0  -1     
21  -1   1   0   =>  0   0   0     0   0   0     
22  -1   1   1   =>  0   0   1     0   0   1     
23  -1   1  -1  =>  -1   1  -1    -1   1  -1     
24  -1  -1   0   => -1  -1   0     0  -1  -1     
25  -1  -1   1   => -1   0   0    -1   0   0     
26  -1  -1  -1  =>  -1  -1  -1    -1  -1  -1     
    -----------------------------------------
     0   0   0       0   0   0    -1   2  -1
```
## State 2 (dynamic):
```
 0   0   0   0   =>  0
 1   0   0   1   =>  2
 2   0   1   0   =>  3
 3   1   0   0   =>  4
 4   0   1   0   =>  1
 
 5   0   0  -1   =>  6
 6   0  -1   0   =>  7
 7  -1   0   0   =>  8
 8   0  -1   0   =>  5
 
 9   0   1   1   =>  10
10   1   0   1   =>  11
11   1   1   0   =>  12
12   1   0   1   =>  9

13   0  -1  -1   =>  14
14  -1   0  -1   =>  15
15  -1  -1   0   =>  16
16  -1   0  -1   =>  13

17   1   1   1   =>  17

18   0   1  -1   =>  0
19   0  -1   1   =>  0
20   1  -1   0   =>  0
21  -1   1   0   =>  0
 
22   1   1  -1   =>  3
23  -1   1   1   =>  1
24   1  -1  -1   =>  5
25  -1  -1   1   =>  7
    
26   1   0  -1   =>  26
27  -1   0   1   =>  27
28   1  -1   1   =>  28
29  -1   0  -1   =>  29
30  -1   1  -1   =>  30
31  -1  -1  -1   =>  31
    -----------------------------------------
      
```

Engine 1 hat in jeden State die Wahrscheinlichkeit 100%.
Engine 2 nur noch 50%, da sich zwei Zustände überlagern.
1. Ein Zustand wird vor dem Run von Engine 1 auf Engine 2 übertragen.
2. Run berechnet den nächsten State der Engines.
3. Nach dem Run werden die Zustände von 2 auf 1 zusammengefasst, wenn die States gleich sind.
   1. Sind die States unterschiedlich, werden die Engines 2 so gelassen.
   2. Eigentlich kann dann vor dem nächsten Run, wenn nötig, von Engine 2 auf Engine 3 übertragen werden.

## Level-Down:
```
a b c d e f a'       State 1
- - - - - - .   
1-1                  a+b
 -1 1                a+b
    1 1              c+d
      1 1            d+e
  b        = -1
1 0                  a+b
  0 1                a+b
    1 1              c+d
      1 1            d+e
    c      = 1
1 0                  a+b
  0 0                a+b
    0 1              c+d
      1 1            d+e

```

```
a b c d e f a'       State 1
- - - - - - .   
1 1                  a+b
 -1 1                a+b
    1 1              c+d
      1 1            d+e
  calc =>
1 1                  a+b
  0 0                a+b
    1 1              c+d
      1 1            d+e
   Level-Down =>
1 1                  a+b
  0 0                a+b
    1 1              c+d
      1 1            d+e
  b           = -
    c         = -
      e       = 1
```

# Idee
Die höheren Level weg von 0 sind der Grundzustand und enhalten niedrige verteilte Energien.
"Hochlevelen" bedeutet eine Konzentration in einem höheren Energiezustand.

Die Engines der verschiedenen Level können verschiedene Regeln haben und verschiedenen Zwecken dienen.

Level 1 konzentriert eher Zustände an einem Ort.
Level 2 verteilt eher Zustände über weitere Orte.
