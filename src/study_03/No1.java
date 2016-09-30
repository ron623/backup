package study_03;

public class No1 {

	// ★No.1～5共通
	// String型で実際に文字列を格納しているのはvalueという名の変数。
	// private char value[]という配列。

	private char value[];

	/*
	 * 　文字列比較 このオブジェクトと同じ文字シーケンスを表すStringオブジェクトがある場合だけtrue
	 *
	 * @param anObject:Stringと比較されるオブジェクト
	 *
	 * @return 指定されたオブジェクトがこの文字列等しいStringならtrue そうでなければfalse
	 */
	public boolean equals(Object anObject) {

		// "this"とはどれをさす？
		// 同じ参照先の場合のみtrueを返す
		// (同じ参照先 ＝　同じ文字列なので。。。）
		if (this == anObject) {
			return true;
		}

		// 同じ文字列のときtrueを返す
		// anObjectがString型の場合
		if (anObject instanceof String) {

			// Object　→　Stringへキャスト
			String anotherString = (String) anObject;

			// "value"はおそらくchar[]配列。（Stringクラスはcharの配列として
			// フィールドを保持しているので。

			// valueはStringクラスの変数なので、length()じゃないと
			// コンパイルエラーになると思うんだけど。。。
			// ・・・いや、、、もしvalueが配列ならlengthでOKか。
			int n = value.length;

			// 比較されるオブジェクト(anotherString)の長さがStringの長さと同じ場合
			// (同じ長さじゃないと同じ文字列になることはあり得ないので。。。）
			// 以下でいうvalueは、String.classのvalue
			if (n == anotherString.value.length) {

				// String文字列をv1[]に代入
				char v1[] = value;
				// 比較されるオブジェクト(anotherString)をv2[]に代入
				char v2[] = anotherString.value;

				// カウンタ
				int i = 0;
				// Stringの長さ分ループ
				while (n-- != 0) {

					// i番目の文字を比較し、異なる場合はfalse
					if (v1[i] != v2[i])
						return false;
					i++;
				}
				// ここまで来れば文字列は等しい
				return true;
			}
		}
		// anObjectｇがString型でない場合、false
		return false;
	}

}
