package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.StateUtils.convertToValue;

import de.schmiereck.col.model.State;

public class StateUtils {

   public static String convertToDebugString(final State[] stateArr) {
      final StringBuilder retStr = new StringBuilder();

      retStr.append("[");
      for (int statePos = 0; statePos < stateArr.length; statePos++) {
         final State inputState = stateArr[statePos];

         if (statePos > 0) {
            retStr.append(",");
         }
         //retStr.append("{");
         retStr.append(convertToValue(inputState));
         //retStr.append("}");
      }
      retStr.append("]");
      return retStr.toString();
   }

   public static String convertToDebugString(final State state) {
      final StringBuilder retStr = new StringBuilder();

      for (int statePos = 0; statePos < state.inputStates.length; statePos++) {
         final State inputState = state.inputStates[statePos];
         final int value = convertToValue(inputState);

         if (statePos > 0) {
            retStr.append(",");
         }
         retStr.append(value);
      }
      return retStr.toString();
   }

   public static String convertInputStatesToDebugString(final State state) {
      final StringBuilder retStr = new StringBuilder();

      for (int statePos = 0; statePos < state.inputStates.length; statePos++) {
         final State inputState = state.inputStates[statePos];
         final int value = convertToValue(inputState);

         if (statePos > 0) {
            retStr.append(",");
         }
         retStr.append(value);
      }
      return retStr.toString();
   }

   public static int convertToValue(final State inputState) {
      final int value;
      if (inputState == negState) {
         value = -1;
      } else {
         if (inputState == nulState) {
            value = 0;
         } else {
            if (inputState == posState) {
               value = 1;
            } else {
               value = 99;
            }
         }
      }
      return value;
   }
}
