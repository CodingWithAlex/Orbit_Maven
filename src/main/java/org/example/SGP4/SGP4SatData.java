package org.example.SGP4;

public class SGP4SatData {
    public int   satnum; // changed to int SEG
    public int    epochyr, epochtynumrev;
    public int    error; // 0 = ok, 1= eccentricity (sgp4),   6 = satellite decay, 7 = tle data
    public char   operationmode;
    public char   init, method;

    public SGP4unit.Gravconsttype gravconsttype; // gravity constants to use - SEG

    /* Near Earth */
    public int    isimp;
    public double aycof  , con41  , cc1    , cc4      , cc5    , d2      , d3   , d4    ,
            delmo  , eta    , argpdot, omgcof   , sinmao , t       , t2cof, t3cof ,
            t4cof  , t5cof  , x1mth2 , x7thm1   , mdot   , nodedot, xlcof , xmcof ,
            nodecf;

    /* Deep Space */
    public int    irez;
    public double d2201  , d2211  , d3210  , d3222    , d4410  , d4422   , d5220 , d5232 ,
            d5421  , d5433  , dedt   , del1     , del2   , del3    , didt  , dmdt  ,
            dnodt  , domdt  , e3     , ee2      , peo    , pgho    , pho   , pinco ,
            plo    , se2    , se3    , sgh2     , sgh3   , sgh4    , sh2   , sh3   ,
            si2    , si3    , sl2    , sl3      , sl4    , gsto    , xfact , xgh2  ,
            xgh3   , xgh4   , xh2    , xh3      , xi2    , xi3     , xl2   , xl3   ,
            xl4    , xlamo  , zmol   , zmos     , atime  , xli     , xni;

    public double a      , altp   , alta   , epochdays, jdsatepoch       , nddot , ndot  ,
            bstar  , rcse   , inclo  , nodeo    , ecco             , argpo , mo    ,
            no;

    // Extra Data added by SEG - from TLE and a name variable (and save the lines for future use)
    public String name="", line1="", line2="";
    public boolean tleDataOk;
    public String classification, intldesg;
    public int nexp, ibexp, numb; // numb is the second number on line 1
    public long elnum,revnum;
}
