import java.util.Scanner;

public class Main {

	static String[][] spreadSheet=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		spreadSheet = readIn();
		for (int i=0;i<spreadSheet.length;i++) {
			for (int j=0;j<spreadSheet[0].length;j++) {
				if (!isNumeric(spreadSheet[i][j])) {
					spreadSheet[i][j]="-";
					spreadSheet[i][j] = evaluate(spreadSheet[i][j]);
				}
			}
		}
		
		print(spreadSheet);
	}

	private static String evaluate(String string) {
		// TODO Auto-generated method stub
		String[] atoms = string.split("+");
		int sum = 0;
		for (String atom:atoms) {
			String value = evaluateAtom(atom);
			if (value == "*")
				return value;
			else 
				sum+=Integer.parseInt(evaluateAtom(atom));
		}
		return ""+sum;
	}

	private static String evaluateAtom(String atom) {
		// TODO Auto-generated method stub
		int[] coord = translateCoord(atom);
		String content = spreadSheet[coord[0]][coord[1]];
		if (content.equals("-")) {
			content = "*";
		}
		else if (!isNumeric(content) && !content.equals("*")) {
			spreadSheet[coord[0]][coord[1]]="-";
			content = evaluate(content);
		}
		spreadSheet[coord[0]][coord[1]] = content;
		return content;
	}

	private static int[] translateCoord(String atom) {
		// TODO Auto-generated method stub
		int[] coord = {atom.charAt(0)-'A', Integer.parseInt(atom.substring(1))-1};
		return coord;
	}

	private static void print(String[][] spreadSheet) {
		// TODO Auto-generated method stub
		for (int i=0;i<10;i++) {
			for (int j=0;j<9;j++) {
				System.out.print(spreadSheet[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static boolean isNumeric(String string) {
		// TODO Auto-generated method stub
		try {
			Integer.parseInt(string);
			return true;
		} catch (Exception  e) {
			return false;
		}
	}

	private static String[][] readIn() {
		// TODO Auto-generated method stub
		String[][] spreadSheet=new String[10][9];
		Scanner sc = new Scanner(System.in);
		for (int i=0;i<10;i++) {
			for (int j=0;j<9;j++) {
				spreadSheet[i][j] = sc.next();
			}
		}
		return spreadSheet;
	}

}
