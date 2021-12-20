package me.lnzt.covtracker.storables;

import java.awt.*;
import java.util.HashMap;

public class ConfigPayload {

    public HashMap<String, CountryNode> COUNTRIES;
    public Dimension configDimension;

    public ConfigPayload(HashMap<String,CountryNode> COUNTRIES, Dimension configDimension){
        this.configDimension = configDimension;
        this.COUNTRIES = COUNTRIES;
    }
}
