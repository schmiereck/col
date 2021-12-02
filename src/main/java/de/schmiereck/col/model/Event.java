package de.schmiereck.col.model;

public class Event {
   public final Event parentUpLevelEvent;
   public boolean levelDownFlag;

   public Event(final Event parentUpLevelEvent) {
      this.parentUpLevelEvent = parentUpLevelEvent;
      this.levelDownFlag = false;
   }
}
