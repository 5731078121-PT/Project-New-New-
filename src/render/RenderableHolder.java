package render;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RenderableHolder {
	private static RenderableHolder instance = new RenderableHolder();
	private static List<IRenderable> entities;

	public RenderableHolder() {
		// TODO Auto-generated constructor stub
		this.entities = new CopyOnWriteArrayList<IRenderable>();
		
	}

	public static RenderableHolder getInstance() {
		return instance;
	}
	
	public void add(IRenderable r){
		entities.add(r);
		Collections.sort(entities, new Comparator<IRenderable>() {
			@Override
			public int compare(IRenderable o1, IRenderable o2) {
				// TODO Auto-generated method stub
				if(o1.getZ() > o2.getZ()) return 1;
				return 0;
			}
		});
		
	}
	
	public static void sort(){
		Collections.sort(entities, new Comparator<IRenderable>() {
			@Override
			public int compare(IRenderable o1, IRenderable o2) {
				// TODO Auto-generated method stub
				if(o1.getZ() > o2.getZ()) return 1;
				return 0;
			}
		});
	}
	
	public static List<IRenderable> getRenderableList(){
		return entities;
	}
	
	public static void clear(){
//		for(IRenderable a : entities){
//			entities.remove(a);
//		}
		entities.clear();
	}
	

}
