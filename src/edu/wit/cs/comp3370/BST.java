package edu.wit.cs.comp3370;

// TODO: document class
public class BST extends LocationHolder {
	
	@Override
	public DiskLocation next(DiskLocation d) {
		// TODO: implement method
		if(d.right != nil){
			return min(d.right);
		}
		else{
			return up(d);
		}
	}
	
	public DiskLocation up(DiskLocation d){
		DiskLocation p = d.parent;
		if(p == nil || d==p.left){
			return p;
		}
		else{
			return up(p);
		}
	}
	
	public DiskLocation min(DiskLocation d){
		while(d.left != nil){
			d = d.left;
		}
		return d;
	}
	
	@Override
	public DiskLocation prev(DiskLocation d) {
		// TODO: implement method
		if(d.left != nil){
			return max(d.left);
		}
		else{
			return down(d);
		}
	}
	
	public DiskLocation down(DiskLocation d){
		DiskLocation p = d.parent;
		if(p == nil || d==p.right){
			return p;
		}
		else{
			return down(p);
		}
	}
	
	public DiskLocation max(DiskLocation d){
		while(d.right != nil){
			d = d.right;
		}
		return d;
	}

	@Override
	public void insert(DiskLocation d) {
		// TODO: implement method
		if(root == null){
			root = d;
			d.left = nil;
			d.right = nil;
			d.parent = nil;
			return;
		}
		d.parent = findParent(d, root, nil);
		if(d.parent.equals(nil)){
			root = d;
		}
		else{
			if(!d.isGreaterThan(d.parent)){
				d.parent.left = d;
				d.right = nil;
				d.left = nil;
			}
			else{
				d.parent.right = d;
				d.right = nil;
				d.left = nil;
			}
		}
	}
	
	public DiskLocation findParent(DiskLocation d, DiskLocation curr, DiskLocation parent){
		if(curr.equals(nil)){
			return parent;
		}
		else if(!d.isGreaterThan(curr)){
			return findParent(d, curr.left, curr);
		}
		else{
			return findParent(d, curr.right, curr);
		}
	}

	@Override
	public int height() {
		// TODO: implement method
		//height in notes
		int h = height(root);
		return h;
	}
	
	public int height(DiskLocation d){
		if(d == nil || (d.left==nil && d.right==nil)){ //if node is null, or it is a leaf
			return 0;
		}
		else{
			return 1 + Math.max(height(d.left), height(d.right));
		}
	}

	@Override
	public DiskLocation find(DiskLocation d) {
		// TODO: implement method
		
		//start from root to search
		return find(d, root);
	}
	
	public DiskLocation find(DiskLocation d, DiskLocation curr){
		if(d == nil){
			return nil;
		}
		if(d.equals(curr))
			return curr;
		else if(!d.isGreaterThan(curr)) //d.sector < curr.sector
			return find(d, curr.left);
		else
			return find(d, curr.right);
	}

}
//9 1 16 1 20 1 3 1 14 1 7 1 19 1 10 1 4 1 24 1 8 1 2 1 12 1 17 1 21 1 5 1 1 1 18 1 15 1 22 1 11 1 6 1 0 1 13 1 23 1 1 1 25 1 p 1 1 2