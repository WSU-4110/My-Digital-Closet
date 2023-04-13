package com.example.mydigitalcloset;

public class Outfit {
    //[0]: name [1]: top [2]: bottoms [3]: shoes [4]: headwear [5]: socks [6]: other

    public String name, top, bottoms, shoes, headwear, socks, other;

    public Outfit(String fit[]){
        name = fit[0];
        top = fit[1];
        bottoms = fit[2];
        shoes = fit[3];
        headwear = fit[4];
        socks = fit[5];
        other = fit[6];
    }

    public String toString(){
        return this.name;
    }

}
