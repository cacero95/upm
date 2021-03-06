/*
 * Author: Stefan Andritoiu <stefan.andritoiu@intel.com>
 * Copyright (c) 2015 Intel Corporation.
 *
 * This program and the accompanying materials are made available under the
 * terms of the The MIT License which is available at
 * https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 */

//NOT TESTED!!!
public class VDiv_Example {
	private static final short gain3 = 3;
	private static final short gain10 = 10;

	public static void main(String[] args) throws InterruptedException {
		// ! [Interesting]
		// Instantiate a Grove Voltage Divider sensor on analog pin A0
		upm_vdiv.VDiv vDiv = new upm_vdiv.VDiv(0);

		// collect data and output measured voltage according to the setting
		// of the scaling switch (3 or 10)
		while (true) {
			long val = vDiv.value(100);
			float gain3val = vDiv.computedValue(gain3, val);
			float gain10val = vDiv.computedValue(gain10, val);

			System.out.println("ADC value: " + val + ", Gain 3: " + gain3val + "v, Gain 10: "
					+ gain10val);

			Thread.sleep(1000);
		}
		// ! [Interesting]
	}

}