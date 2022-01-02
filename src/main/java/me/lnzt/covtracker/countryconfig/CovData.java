package me.lnzt.covtracker.countryconfig;

public class CovData {
    public String ACTIVE_CASES;
    public String TOTAL_INFECTIONS;
    public String TOTAL_DEATHS;
    public String POPULATION;
    public String RECOVERED;
    public CovData(String activeCases, String totalInfections, String totalDeaths, String population, String recovered){
        this.ACTIVE_CASES = activeCases;
        this.TOTAL_INFECTIONS = totalInfections;
        this.TOTAL_DEATHS = totalDeaths;
        this.POPULATION = population;
        this.RECOVERED = recovered;
    }



    public double getAverageWCases(double base){
        double active_cases = -1;
        try {
            active_cases = Double.valueOf(TOTAL_INFECTIONS.replace(",", ""));
        }catch (Exception e){

        }
        return active_cases/base;

    }


}
