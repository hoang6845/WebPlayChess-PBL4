package com.pbl4.model.bean;

import java.util.Stack;

public class Castle extends Chess {
	public Castle(Castle other) {
        super(other);
        // Sao chép các thuộc tính đặc biệt của Castle
    }
	
	public Castle(){
		
	}
	@Override
	public void setValue() {
		this.value = 50;
	}
	@Override
	public void PositiveMove(int[][] Board) {
		if (this.value==50) {
			int k = this.getIndex();
			for (int i = P.x + 1; i < 8; i++) {
				if (Board[i][P.y] != 0) {
					if (Board[i][P.y] * k < 0)
						this.updatePnew(i, P.y);
					break;
				}
				this.updatePnew(i, P.y);
			}
			for (int i = P.x - 1; i >= 0; i--) {
				if (Board[i][P.y] != 0) {
					if (Board[i][P.y] * k < 0)
						this.updatePnew(i, P.y);
					break;
				}
				this.updatePnew(i, P.y);
			}
			for (int i = P.y + 1; i < 8; i++) {
				if (Board[P.x][i] != 0) {
					if (Board[P.x][i] * k < 0)
						this.updatePnew(P.x, i);
					break;
				}
				this.updatePnew(P.x, i);
			}
			for (int i = P.y - 1; i >= 0; i--) {
				if (Board[P.x][i] != 0) {
					if (Board[P.x][i] * k < 0)
						this.updatePnew(P.x, i);
					break;
				}
				this.updatePnew(P.x, i);
			}
			
		}
	}
//	public static void main(String ar[]) {
//		Chess p = new Castle();
//		p.setP(1, 2);
//		p.setValue();
//		p.setIndex(1);
//		p.PositiveMove();
//		while (!p.Pnew.isEmpty()) {
//			Location t = p.Pnew.pop();
//			System.out.print(t.x + ", " + t.y + " ; ");
//		}
//		System.out.print("\n");
//		Stack<Location> t = p.ValidMove();
//		while (!t.isEmpty()) {
//			Location k = t.pop();
//			System.out.print(k.x + ", " + k.y + " ; ");
//		}
//	}
}
