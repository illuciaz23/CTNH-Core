package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import io.github.cpearl0.ctnhcore.registry.nuclear.NuclearElements;

public class CTNHElements {
    public static void init() {
        NuclearElements.init();
    }
    public static final Element STARMETAL = new Element( 120, 138, -1, null,"starmetal", "St", false);
    public static final Element ADAMANTINE = new Element( 119, 134, -1, null,"adamantine", "Ad", false);
    public static final Element TARANIUM = new Element( 121, 140, -1, null, "taranium","Tn", false);
    public static final Element INFINITY = new Element(114514, 1919810, -1, null, "infinity", "∞", false);
    public static final Element SUPERMANA = new Element(169, 169, -1, null, "super_mana","**Ma**", false);
    public static final Element MANA = new Element( 169, 169, -1, null, "mana","Ma", false);
    public static final Element MANA_MIXED_1 = new Element( 169, 169, -1, null, "mana_mixed_1","????Ma????", false);
    public static final Element MANA_MIXED_2 = new Element(169, 169, -1, null, "mana_mixed_2", "???Ma???", false);
    public static final Element MANA_LP_MIXED = new Element(169, 169, -1, null, "mana_lp_mixed", "??Ma??LP+", false);
    public static final Element MANA_DEMON_MIXED = new Element(169, 169, -1, null, "mana_demon_mixed", "?Ma?LP++", false);
    public static final Element MANA_PLUS2 = new Element(300, 300, -1, null, "mana_plus2", "Ma+", false);
    public static final Element MANA_PLUS3 = new Element(300, 300, -1, null, "mana_plus3", "Ma++++", false);
    public static final Element Antihydrogen= new Element(-1,1,-1,null,"h_bar","H-",false);
    public static final Element Antioxygen= new Element(-1,-1,-1,null,"o_bar","O-",false);
    public static final Element Inf_bar= new Element(-32678,-32678,-1,null,"inf_bar","-∞",false);
    public static final Element SHADOWSTEEL = new Element(121, 140, -1, null, "shadowsteel", "Sd", false);
    public static final Element PYROSCALE = new Element(122, 145, -1, null, "pyroscale", "Pr", false);
    public static final Element GLACIAL_CORE = new Element(123, 142, -1, null, "glacial_core", "Gc", false);
    public static final Element PHANTOM_IRON = new Element(124, 148, -1, null, "phantom_iron", "Pn", false);
    public static final Element BOG_AMBER = new Element(125, 138, -1, null, "bog_amber", "Bm", false);
    public static final Element STORMVEIN = new Element(128, 150, -1, null, "stormvein", "Sv", false);
    public static final Element SUNNARIUM = new Element(128, 150, -1, null, "sunnarium", "☼", false);
    public static final Element HIKARIUM = new Element(12800, 15000, -1, null, "hikarium", "☀", false);
    public static final Element END_OF_OIL=new Element(1000,1000,-1,null,"end_of_oil","END",false);
    public static final Element Living_Metal=new Element(1000,1000,-1,null,"living_metal","*Sn?Pb?&LM*Ω",false);
    public static final Element COLORFUL_GEM=new Element(1000,1000,-1,null,"colorful_gem","§cC§eO§aL§9O§dR",false);
}
