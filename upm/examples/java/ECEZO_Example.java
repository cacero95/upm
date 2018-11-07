/*
 * Author: Jon Trulson <jtrulson@ics.com>
 * Copyright (c) 2016 Intel Corporation.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import upm_ecezo.ECEZO;

public class ECEZO_Example
{
    public static void main(String[] args) throws InterruptedException
    {
// ! [Interesting]

         // Instantiate a ECEZO sensor on uart 0 at 9600 baud.
        ECEZO sensor = new ECEZO(0, 9600, false);

        // For I2C, assuming the device is configured for address 0x64 on
        // I2C bus 0, you could use something like:
        //
        // ECEZO sensor = new ECEZO(0, 0x64, true);

        while (true)
            {
                // update our values from the sensor
                sensor.update();

                System.out.println("EC "
                                   + sensor.getEC()
                                   + " uS/cm, TDS "
                                   + sensor.getTDS()
                                   + " mg/L, Salinity "
                                   + sensor.getSalinity()
                                   + " PSS-78, SG "
                                   + sensor.getSG());

                Thread.sleep(5000);
            }

// ! [Interesting]
    }
}
