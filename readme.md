
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
Er kann folgende Zust??nde annehmen: -1, 0, 1

Wenn ein Automat den Zustand einer anderen Zelle als Eingang benutzt, 
muss er auch deren Ausgangszustand bestimmen.

Eingangs-Zust??nde sind Zustand einer Zelle .
Ausgang ist der n??chste Zustand des 

Wenn ein Ausgang von mehreren Engines gesetzt wird,
??berlagern sich die Zust??nde.
=> Wie werden die aufgel??st, wenn es um die Ermittlung eines Eingangswertes geht?

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

## State 1 (static):
```
0    0   0   =>   0
1    0   1   =>   1
2    0  -1   =>   2
3    1   0   =>   3
4    1   1   =>   4
5    1  -1   =>   0
6   -1   0   =>   6
7   -1   1   =>   0
8   -1  -1   =>   8
    ----------------------------
     0   0        
```

## State 1 (dynamic):
horizontal:
```
0    0   0   =>   0
1    0   1   =>   2
2    1   0   =>   1
3    0  -1   =>   4
4   -1   0   =>   3
5    1   1   =>   5
6    1  -1   =>   0
7   -1   1   =>   0
8   -1  -1   =>   8
    ----------------------------
     0   0        
```
vertical (meta):
```
0+x:
0  =>  0
    0        0   0   =>   0        0   0
    0    0   0       =>   0    0   0  
1  =>  1
    1        0   1   =>   1        0   1
    0    0   0       =>   0    0   0  
2  =>  9
    2        1   0   =>   0        0   0
    0    0   0       =>   1    0   1
3  =>  3
    3        0  -1   =>   3        0  -1
    0    0   0       =>   0    0   0  
4  =>  3
    4       -1   0   =>   0        0   0
    0    0   0       =>   3    0  -1
5  =>  10
    5        1   1   =>   1        0   1
    0    0   0       =>   1    0   1
6  =>  12
    6        1  -1   =>   3        0  -1
    0    0   0       =>   1    0   1 
7  =>  ?
    7       -1   1   =>   1        0   1
    0    0   0       =>   3    0  -1 
8  =>  ?
    8       -1  -1   =>   3        0  -1
    0    0   0       =>   3    0  -1  

1+x:
9   =>  2
    0        0   0   =>   2        1   0
    1    0   1       =>   0    0   0
10  => 5
    1        0   1   =>   5        1   1
    1    0   1       =>   0    0   0
11  => 11
    2        1   0   =>   2        1   0
    1    0   1       =>   1    0   1
12  => 6
    3        0  -1   =>   6        1  -1
    1    0   1       =>   0    0   0
13  => 0
    4       -1   0   =>   0        0   0
    1    0   1       =>   0    0   0
14  => 14
    5        1   1   =>   5        1   1
    1    0   1       =>   1    0   1
15  => 15
    6        1  -1   =>   6        1  -1
    1    0   1       =>   1    0   1
16  => 1
    7       -1   1   =>   1        0   1
    1    0   1       =>   0    0   0
17  => 3
    8       -1  -1   =>   3        0  -1
    1    0   1       =>   0    0   0

2+x:
18  => 18
    0        0   0   =>   0        0   0
    2    1   0       =>   2    1   0
...

    0    0   0   =>   0
    1    0   1   =>   2
    2    1   0   =>   1
    3    0  -1   =>   4
    4   -1   0   =>   3
    5    1   1   =>   5
    6    1  -1   =>   0
    7   -1   1   =>   0
    8   -1  -1   =>   8
    ----------------------------
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
Engine 2 nur noch 50%, da sich zwei Zust??nde ??berlagern.
1. Ein Zustand wird vor dem Run von Engine 1 auf Engine 2 ??bertragen.
2. Run berechnet den n??chsten State der Engines.
3. Nach dem Run werden die Zust??nde von 2 auf 1 zusammengefasst, wenn die States gleich sind.
   1. Sind die States unterschiedlich, werden die Engines 2 so gelassen.
   2. Eigentlich kann dann vor dem n??chsten Run, wenn n??tig, von Engine 2 auf Engine 3 ??bertragen werden.

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

# Ideen
## Urzustand: Felder zu Teilchen
Die h??heren Level weg von 0 sind der Grundzustand und enhalten niedrige verteilte Energien.
"Hochlevelen" bedeutet eine Konzentration in einem h??heren Energiezustand.

Die Engines der verschiedenen Level k??nnen verschiedene Regeln haben und verschiedenen Zwecken dienen.

Level 1 konzentriert eher Zust??nde an einem Ort.
Level 2 verteilt eher Zust??nde ??ber weitere Orte.

## Engine-Generator
Regeln f??r neue Regeln:
* Folgezustand hat die gleiche Anzahl von +/-States.
* Folgezustand darf sich nur an einer Stelle ??ndern (Zustand bewegt sich von Zelle zur Nachbar-Zelle).

## horizontal AND vertical
* Jeder Status sieht in seinem Level die ihn ??berschneidenden Status.
* Er benutzt sie um seinen Folgestatus zu bestimmen.
* Er kann auch den Status der Meta-Status beeinflussen, darf aber nur die gro?? geschriebenen ver??ndern.
  * Alternative - Es wird ein eigener Rechenschritt zur Berechnung des vertikalen Status ausgef??hrt (mit einer eigenen Engine?).

Level 1:
```
A-N:
          mmAAcc
           nNbb
B-A:
           nnBB
          mmaAcc
C-B:
          mmaaCC
           nnbBdd
D-e:
           nnbbDD
          mmaacCee
```
Level 2:
```
A-M-N:
                     AAAddd
                   mmMbbb
                    nNnccc
B-N-A:
                   mmmBBB
                    nnNccc
                     aAaddd
C-A-B:
                    nnnCCC
                     aaAddd
                   bbbbBb
D-B-C:
                     aaaDDD
                   mmmbbBeee
                    nnncCc
E-C-D:
                   mmmbbbEEE
                    nnnccC
                     aaadDd
...
```

# Bewegung
Ist ein eigener Zweig in der Engine der die Folgezust??nde nicht mehr symmetrisch bestimmt, sondern nach rechts oder links.
In den entsprechenden Zweig zu kommen entspricht einen Impuls von au??en.

## Level 0  0=(1 + (1 * 1))   0
## Level 1  2=(1 + (1 * 1))   1  1  3  2-0*1
```
      //    5    0       =>   0    0   .
      //    0        1   =>   5        0  (1)                              // Move-Meta-Right (+1)
```
## Level 2  2=(1 + (2 * 2))   4  3  5  3-1*1
```
Left:
      //    4        1   0           =>   4.   .   1   0                   // Move-Meta-Left (-2)
      //    0            0   0       =>   0   (1)  .   0   0
      
Right:
      //    9        1   0           =>   9        0   0                   // Move-Meta-Right
      //    0            0   0       =>   0            1   0

      //    0        0   0           =>   0        0   0  (1)  .
      //    9            1   0       =>   9            0   0   .   .       // Move-Meta-Right (+2)
```
## Level 3  3=(1 + (3 * 3))   9  5  7  4-2*1
```
      //    *    1   0   0               =>   *    0   0   0               // Move-Meta-Right
      //    9        0   0   0           =>   9        1   0   0
      //    0            0   0   0       =>   0            0   0   0

```
## Level 4  4=(1 + (4 * 4))   16  7  9  5-3*1

# Hyper-Parts
```
Level 3 & 3:    min-diff: 3+3-1
   ###+++
    ###+++
     ###+++
     0123456789     0   3
   ###   +++
    ###   +++
     ###   +++
     0123456789     0   6
Level 3 & 2:    min-diff: 3+2-1
   ### 
    ###  
     ###
    ++
     ++
     0123456789     0   0
   ### 
    ###  
     ###
      ++
       ++
     0123456789     0   2
   ### 
    ###  
     ###
        ++
         ++
     0123456789     0   4
```

```
      //          0   1   2   3
      //              -   S
      //                  -   -    
      //              R   0   0   
      //                  0   0   0   
      //                      0   0   0
      // =>
      //  -   -   -
      //      -   -   L
      //          -   -   -

Level 0:          1   1
Level 1:        2 1   3    6
Level 2:      3 2 1   6   12
Level 3:    6 3 2 1  12   24
Level 3: 12 6 3 2 1  24   28

Level 0:          1   1
Level 1:        2 1   3    
Level 2:      4 2 1   7   
Level 3:    8 4 2 1  15   
Level 3: 16 8 4 2 1  31   

A Reflection: verry good, but a lot of Parts
                         (S)
              3R  2   1         6
  => Part
              4R  2             6
                      1L        2/2
  => State
                  4R  2         12
                  1L            4/2
  => Part
                  4R            4
                      2L        4/2
                  1L            4/4
  => State
                      1R        3
                  1L            2
              1L                1
  => Part
                      1L        
                  1L            
              1L            
B Reflection: ok, but stange
                         (S)
                  3R  2   1         6
  => Part
                  3R                3
                  1   2L            3
  => State
                      3R            3
              1   2L                3
  => Part
                      3L            3
              1   2L                1,5
              -----------
              1   2   3
C Reflection: bit bad ?
                         (S)
                  3R  2   1          
  => Part
                  6R                 
                  3R  2   1          
                  1   2L             
  => State
                      1R
                      3R  2   1 
              1   2L    
  => Part
                      3L                3L   3 + 6/2 + 3/2  warum 3L???
              1   2   3L        1   2   3L = 6
              1   2L            1   2      = 3
              -----------
              1   2   4,5?      2   4   6
                                1   2   3
D Reflection: bad!
                         (S)
                  3R  2   1
  => Part
                  3R  2   1
                  1   2L
  => State
                      8R  2   1 
              1   2L
  => Part
              1   2   8L
              1   2L

```

# Spiegelung
 ( s, l, r )

   0, 0, 5
=> 0, 5, 0

 ( sm11 lm12 rm13 )
 ( sm21 lm22 rm23 )
 ( sm31 lm32 rm33 )
s = s*sm11 + l*lm12 + r*rm13
l = s*sm21 + l*lm22 + r*rm23
r = s*sm31 + l*lm32 + r*rm33

 ( 0  0  0)
 ( 0  0  1)
 ( 0  1  0)


   0, 1, 5
=> 0, 5, 1

# Probability

```
    X X
    -> X
    --> X
    -> ->
    --> ->
    -> -->
    -> <-
    --> <-
    -> <--
    X ->
    
				a-b
			a	d=	b
		r	30	0	30
		s	70	0	70
		l	0	0	0

			a-d		b+d
			=a		=b
		r	30		30
		s	70		70
		l	0		0


```

## right (fast:20) to right (slow:10)
```
				a-b
			a	d=	b
		r	30	30	0
		s	70	-30	100
		l	0	0	0

			a-d		b+d
			=a		=b
		r	0		30
		s	100		70
		l	0		0

				a-b
			a	=d	b
		su	30	-30	60
		sd	70	30	40
		r	20	10	10
		s	80	-10	90
		l	0	0	0

			a-d		b+d
			=a		=b
		su	60		30
		sd	40		70
		r	10		20
		s	90		80
		l	0		0
```
