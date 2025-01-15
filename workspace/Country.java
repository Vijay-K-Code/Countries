public class Country
{
  // add private instance variables for the name, capital, language, and image file.

  // add constructors

  // Write accessor/get methods for each instance variable that returns it.

  // Write a toString() method that returns a concatenated String of 3 of the instance variables in a sentence like "..'s capital is .. and its primary language is ..."
 
ublic class Country {
    // Instance variables
    private String name;
    private String capital;
    private String primaryLanguage;
    private String imageFile;

    // Default constructor
    public Country() {
        this.name = "";
        this.capital = "";
        this.primaryLanguage = "";
        this.imageFile = "";
    }

    // Constructor with 4 arguments
    public Country(String name, String capital, String primaryLanguage, String imageFile) {
        this.name = name;
        this.capital = capital;
        this.primaryLanguage = primaryLanguage;
        this.imageFile = imageFile;
    }

    // Accessor methods
    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public String getImageFile() {
        return imageFile;
    }

    // toString method
    @Override
    public String toString() {
        return name + "'s capital is " + capital + " and its primary language is " + primaryLanguage + ".";
    }
}

  
}
