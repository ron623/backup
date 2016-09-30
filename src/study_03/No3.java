package study_03;


public class No3 {

	/*
	 * 文字[ 1 ]がこのStringオブジェクトによってあらわされる文字列内にない場合は
	 * このStringオブジェクトへの参照が返される。（＝　元の文字のままとなる）
	 *
	 * ある場合、このStringオブジェクトによってあらわされる文字列と同じ文字列を表す、
	 * 新しいStringが生成される。（　＝　新しい文字で変換される）
	 *
	 * ただし、文字列内の[ 1 ]はすべて[ 2 ]に置換される。
	 *
	 *
	 *
	 * @param [ 1 ]oldChar　以前の文字
	 *
	 * @param [ 2 ]newChar　新しい文字
	 *
	 * @return この文字列内のすべての[ 1 ]を [ 2 ]に置換することによって生成された文字列
	 */

	public String replace(char oldChar, char newChar) {

		// 以前の文字と新しい文字が異なる場合
		if (oldChar != newChar) {
			// Stringの長さをlenに代入
			int len = value.length;

			// ★なぜ0からでなく-1から始まってるのか？？？
			int i = -1;

			// char[]配列valにvalue(String)を代入
			char[] val = value;

			// Stringの長さ分ループ
			while (++i < len) { // lenが0より大きい
				// Stringのi番目と以前の文字に同じものが出てきた時点で処理中断
				// 外側のif文で比較した文字以降に、同じ文字があるかどうかチェック
				if (val[i] == oldChar) {
					break;
				}
			}

			// Stringの長さ分↑のループが回らなかった場合
			// ( ＝　↑ で比較した文字以降に同じ文字があった場合）
			if (i < len) {

				// Stringと同じ要素数のchar[]配列bufを作成
				char buf[] = new char[len];

				// bufにStringの要素を１つずつ入れていく
				for (int j = 0; j < i; j++) {
					buf[j] = val[j];
				}
				// Stringの文字数分ループ
				while (i < len) {

					char c = val[i];

					// cが以前の文字と等しい場合新しい文字を、異なる場合はcを代入
					buf[i] = (c == oldChar) ? newChar : c;
					i++;
				}
				// Stringオブジェクトによってあらわされる文字列と同じ文字列を表す 新しいStringが生成
				// ★newするときchar[]とtrueを渡すコンストラクタは存在しないが・・・
				return new String(buf, true);
			}
		}
		//  このStringオブジェクトへの参照が返される
		return this;
	}
}
