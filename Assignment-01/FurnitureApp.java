class FurnitureApp{
	public static void main(String []args){		
		
		
		Chair chair=new Chair(); 
		chair.addChair(2);
		Bed bed=new Bed();
		bed.addBed(5);
		
		List list=new List();
		
		System.out.println("Number of Bed: "+list.chairCount);
		
		System.out.println("Number of Chair: "+list.bedCount);
	}

}