package de.schmiereck.col.model;

public class Cell {
   //public int stateLevel;
   /**
    * Engine-State of the Cell.
    */
   public int statePos;
   /**
    * Engine-Meta-State of the Cell and their direct neighbors on the left.
    */
   public int metaStatePos;
   //public Cell[] metaCellArr;
   public Event event;
}
