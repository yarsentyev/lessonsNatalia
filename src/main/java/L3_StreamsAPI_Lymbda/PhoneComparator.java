package L3_StreamsAPI_Lymbda;

import java.util.Comparator;

public class PhoneComparator implements Comparator<Phone> {
  public int compare(Phone a, Phone b){
    return a.getName().toUpperCase().compareTo((b.getName().toUpperCase()));
  }

}
