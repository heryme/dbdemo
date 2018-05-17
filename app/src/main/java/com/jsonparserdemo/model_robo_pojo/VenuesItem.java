package com.jsonparserdemo.model_robo_pojo;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class VenuesItem{

	@SerializedName("hasPerk")
	private boolean hasPerk;

	@SerializedName("specials")
	private Specials specials;

	@SerializedName("referralId")
	private String referralId;

	@SerializedName("venueChains")
	private List<Object> venueChains;

	@SerializedName("verified")
	private boolean verified;

	@SerializedName("beenHere")
	private BeenHere beenHere;

	@SerializedName("hereNow")
	private HereNow hereNow;

	@SerializedName("stats")
	private Stats stats;

	@SerializedName("contact")
	private Contact contact;

	@SerializedName("name")
	private String name;

	@SerializedName("location")
	private Location location;

	@SerializedName("id")
	private String id;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	@SerializedName("allowMenuUrlEdit")
	private boolean allowMenuUrlEdit;

	public void setHasPerk(boolean hasPerk){
		this.hasPerk = hasPerk;
	}

	public boolean isHasPerk(){
		return hasPerk;
	}

	public void setSpecials(Specials specials){
		this.specials = specials;
	}

	public Specials getSpecials(){
		return specials;
	}

	public void setReferralId(String referralId){
		this.referralId = referralId;
	}

	public String getReferralId(){
		return referralId;
	}

	public void setVenueChains(List<Object> venueChains){
		this.venueChains = venueChains;
	}

	public List<Object> getVenueChains(){
		return venueChains;
	}

	public void setVerified(boolean verified){
		this.verified = verified;
	}

	public boolean isVerified(){
		return verified;
	}

	public void setBeenHere(BeenHere beenHere){
		this.beenHere = beenHere;
	}

	public BeenHere getBeenHere(){
		return beenHere;
	}

	public void setHereNow(HereNow hereNow){
		this.hereNow = hereNow;
	}

	public HereNow getHereNow(){
		return hereNow;
	}

	public void setStats(Stats stats){
		this.stats = stats;
	}

	public Stats getStats(){
		return stats;
	}

	public void setContact(Contact contact){
		this.contact = contact;
	}

	public Contact getContact(){
		return contact;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	public void setAllowMenuUrlEdit(boolean allowMenuUrlEdit){
		this.allowMenuUrlEdit = allowMenuUrlEdit;
	}

	public boolean isAllowMenuUrlEdit(){
		return allowMenuUrlEdit;
	}
}