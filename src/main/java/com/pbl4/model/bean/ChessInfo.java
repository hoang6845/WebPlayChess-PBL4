package com.pbl4.model.bean;

public class ChessInfo {
	private String pieceColor;
	private String pieceId;
	private String pieceType;
	private String squareId;
	
	public static ChessInfo getInstance() {
		return new ChessInfo();
	}
	
	public ChessInfo(String pieceColor, String pieceId, String pieceType, String squareId) {
		super();
		this.pieceColor = pieceColor;
		this.pieceId = pieceId;
		this.pieceType = pieceType;
		this.squareId = squareId;
	}
	
	public ChessInfo() {
		
	}
	
	public String getPieceColor() {
		return pieceColor;
	}
	public void setPieceColor(String pieceColor) {
		this.pieceColor = pieceColor;
	}
	public String getPieceId() {
		return pieceId;
	}
	public void setPieceId(String pieceId) {
		this.pieceId = pieceId;
	}
	public String getPieceType() {
		return pieceType;
	}
	public void setPieceType(String pieceType) {
		this.pieceType = pieceType;
	}
	public String getSquareId() {
		return squareId;
	}
	public void setSquareId(String squareId) {
		this.squareId = squareId;
	}
	public ChessInfo convertChessToChessInfo(Chess A,int i) {
		String pieceColor = new String();
		String pieceId = new String();
		String pieceType = new String();
		String squareId = new String();
		
		if (A.getIndex()>0) {
			pieceColor = "black";
		}else if (A.getIndex()<0) {
			pieceColor = "white";
		}

		if (pieceColor.equals("black")) {
			if (i<=7&&i>=0) {
				StringBuilder str = new StringBuilder("pawn");
				pieceType="pawn";
				str.append(((char)('a'+i)));
				str.append("7");
				pieceId = str.toString();
			}else if (i==8||i==9) {
				StringBuilder str = new StringBuilder("rook");
				pieceType = "rook";
				if (i==8) {
					str.append("a");
				}else str.append("h");
				str.append("8");
				pieceId = str.toString();
			}else if(i==10||i==11) {
				StringBuilder str = new StringBuilder("knight");
				pieceType = "knight";
				if (i==10) {
					str.append("b");
				}else str.append("g");
				str.append("8");
				pieceId = str.toString();
			}else if(i==12||i==13) {
				StringBuilder str = new StringBuilder("bishop");
				pieceType = "bishop";
				if (i==12) {
					str.append("c");
				}else str.append("f");
				str.append("8");
				pieceId = str.toString();
			}else if (i==14) {
				StringBuilder str = new StringBuilder("queen");
				pieceType = "queen";
				str.append("d");
				str.append("8");
				pieceId = str.toString();
			}else if (i==15) {
				StringBuilder str = new StringBuilder("king");
				pieceType = "king";
				str.append("e");
				str.append("8");
				pieceId = str.toString();
			}
			
		}else if (pieceColor.equals("white")) {
			if (i<=7&&i>=0) {
				StringBuilder str = new StringBuilder("pawn");
				pieceType = "pawn";
				str.append(((char)('a'+i)));
				str.append("2");
				pieceId = str.toString();
			}else if (i==8||i==9) {
				StringBuilder str = new StringBuilder("rook");
				pieceType = "rook";
				if (i==8) {
					str.append("a");
				}else str.append("h");
				str.append("1");
				pieceId = str.toString();
			}else if(i==10||i==11) {
				StringBuilder str = new StringBuilder("knight");
				pieceType = "knight";
				if (i==10) {
					str.append("b");
				}else str.append("g");
				str.append("1");
				pieceId = str.toString();
			}else if(i==12||i==13) {
				StringBuilder str = new StringBuilder("bishop");
				pieceType = "bishop";
				if (i==12) {
					str.append("c");
				}else str.append("f");
				str.append("1");
				pieceId = str.toString();
			}else if (i==14) {
				StringBuilder str = new StringBuilder("queen");
				pieceType = "queen";
				str.append("d");
				str.append("1");
				pieceId = str.toString();
			}else if (i==15) {
				StringBuilder str = new StringBuilder("king");
				pieceType = "king";
				str.append("e");
				str.append("1");
				pieceId = str.toString();
			}
		}
		if (A.getValue()==-1) {
			pieceColor = "blackDie";
		}
		else if(A.getValue()==1) {
			pieceColor = "whiteDie";
		}else {
			StringBuilder str  = new StringBuilder();
			str.append((char)(A.getP().getX()+'a'));
			str.append((char)('8'-A.getP().getY()));
			squareId = str.toString();			
		}
	
		return new ChessInfo(pieceColor, pieceId, pieceType, squareId);
		
	}
	
}
