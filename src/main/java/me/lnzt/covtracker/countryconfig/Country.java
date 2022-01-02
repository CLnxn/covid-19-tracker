package me.lnzt.covtracker.countryconfig;

import me.lnzt.covtracker.exceptions.CoordinateOutOfBoundsException;

public class Country {

    public String ISO31662TAG, countryName;
    public CovData data;

    private Location location;

    /**
     *  constructs a Country instance based on the non-null locational, iso3166-2 tag and country name data provided.
     * @param tag
     * @param latitude
     * @param longitude
     * @param countryName
     * @throws CoordinateOutOfBoundsException
     */
    public Country(String tag, double latitude, double longitude, String countryName) throws CoordinateOutOfBoundsException {
        this.ISO31662TAG = tag;
        this.countryName = countryName;
        this.location = new Location(latitude, longitude);

    }

    /**
     * this constructor is only used for configuring the 'World' country. No implementation has been defined for any other countries. Use the overload instead.
     * @param countryName
     */
    public Country(String countryName){
        this.countryName = countryName;
        this.ISO31662TAG = countryName;

    }

    public void setData(CovData data) {
        this.data = data;
    }

    public Location getLocation(){

        return this.location;
    }


}
