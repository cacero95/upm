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

import upm_mcp2515.MCP2515;

public class MCP2515_TXRX_Example
{
    public static void main(String[] args) throws InterruptedException
    {
// ! [Interesting]

        // by default we just receive packets
        Boolean do_tx = false;
        if (args.length > 0)
            do_tx = true;

        // Instantiate a MCP2515 on SPI bus 0 using a hw CS pin (-1).
        MCP2515 sensor = new MCP2515(0, -1);

        // NOTE: This example assumes that only two devices are connected
        // to the CAN bus, and that both devices are running this example;
        // one in TX (transmit) mode, and one in RX (receive) mode.

        // If this is not the case, then in rx mode you may see other
        // packets from other devices, or not, depending on whether the
        // speed is correct for the network.  In tx mode, errors may be
        // generated on the bus, especially if the CAN bus speed does not
        // match the rest of the network.

        // You should start the receiver example first.  The transmitter
        // example will fail after about 5 seconds (timeout) of not being
        // able to transmit a message.

        while (true)
        {
            if (do_tx)
            {
                // lets build up a packet and try loading it (8 bytes max).
                String myPayload = "01234567";
                System.out.println("Loading a packet of 8 numbers (0-7) into a TX buffer...");
                sensor.loadTXBuffer(upm_mcp2515.MCP2515_TX_BUFFER_T.MCP2515_TX_BUFFER0,
                                    0, false, false, myPayload);

                // now lets try to transmit it
                System.out.println("Transmitting packet...");
                sensor.transmitBuffer(upm_mcp2515.MCP2515_TX_BUFFER_T.MCP2515_TX_BUFFER0,
                                      true);

                System.out.println("Transmit successful");
                System.out.println();
                Thread.sleep(500);
            }
            else
            {
                // There should now be a packet waiting for us in RXB0
                if (sensor.rxStatusMsgs() == upm_mcp2515.MCP2515_RXMSG_T.MCP2515_RXMSG_RXB0)
                {
                    System.out.println("Packet received in RXB0, decoding...");

                    // now lets retrieve and print it
                    sensor.getRXMsg(upm_mcp2515.MCP2515_RX_BUFFER_T.MCP2515_RX_BUFFER0);

                    sensor.printMsg();
                    System.out.println();
                }

                Thread.sleep(100);
            }
        }

// ! [Interesting]
    }
}
