class FurnitureApp{
    public static void main(String []args){
        
        furnitures.bedroom.Bed bedroomBed=new furnitures.bedroom.Bed();   
		furnitures.livingroom.Chair livingroomChair=new furnitures.livingroom.Chair();		
		furnitures.livingroom.Table livingroomTable=new furnitures.livingroom.Table();
		furnitures.office.Chair officeChair= new furnitures.office.Chair();
		furnitures.office.Table officeTable=new furnitures.office.Table();
        
		System.out.printf("The price of furnitures.bedroom.Bed is %s \n",bedroomBed.price());
		System.out.printf("The price of furnitures.livingroom.Chair is %s \n",livingroomChair.price());
		System.out.printf("The price of furnitures.livingroom.Table is %s \n",livingroomTable.price());
		System.out.printf("The price of furnitures.office.Chair is %s  \n",officeChair.price());
		System.out.printf("The price of furnitures.office.Table is %s  \n",officeTable.price());
		
    }
} 