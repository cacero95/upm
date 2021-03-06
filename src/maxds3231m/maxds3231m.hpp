/*
 * Author: Yevgeniy Kiveisha <yevgeniy.kiveisha@intel.com>
 * Copyright (c) 2014 Intel Corporation.
 *
 * This program and the accompanying materials are made available under the
 * terms of the The MIT License which is available at
 * https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 */
#pragma once

#include <string>
#include <mraa/i2c.hpp>

#define ADDR                    0x68 // device address

// timekeeping registers
#define TIME_CAL_ADDR           0x00
#define ALARM1_ADDR             0x07
#define ALARM2_ADDR             0x0B
#define CONTROL_ADDR            0x0E
#define STATUS_ADDR             0x0F
#define AGING_OFFSET_ADDR       0x10
#define TEMPERATURE_ADDR        0x11

// control register bits
#define A1IE                    0x1
#define A2IE                    0x2
#define INTCN                   0x4

// status register bits
#define A1F                     0x1
#define A2F                     0x2
#define OSF                     0x80

#define HIGH                    1
#define LOW                     0

namespace upm {

struct Time3231 {
    uint8_t second;
    uint8_t minute;
    uint8_t hour;
    uint8_t day;
    uint8_t month;
    int16_t year;
    uint8_t weekDay;
};

/**
 * @brief MAXDS3231M Proximity Sensor
 * @defgroup maxds3231m libupm-maxds3231m
 * @ingroup maxim i2c light
 */
/**
 * @library maxds3231m
 * @sensor maxds3231m
 * @comname Realtime Clock (RTC) Sensor Library
 * @type light
 * @man maxim
 * @con i2c
 * @web https://www.maximintegrated.com/en/products/digital/real-time-clocks/DS3231.html
 *
 * @brief API for the MAXDS3231M I2C Real-Time Clock
 *
 * This module defines the API for MAXDS3231M
 *
 * @snippet maxds3231m.cxx Interesting
 */
class MAXDS3231M {
    public:
        /**
         * Instantiates an MAXDS3231M object
         *
         * @param bus Number of the used bus
         * @param devAddr Address of the used I2C device
         */
        MAXDS3231M (int bus=0, int devAddr=0x68);

        /**
         * Sets the date and time on the chip.
         *
         * @param time Time structure
         */
        void setDate (Time3231 &time);

        /**
         * Gets the date and time from the chip.
         *
         * @param time Time structure
         */
        bool getDate (Time3231 &time);

        /**
         * Gets the on-board temperature.
         */
        uint16_t getTemperature ();

        /**
         * Returns the name of the component
         */
        std::string name()
        {
            return m_name;
        }
    private:
        std::string m_name;

        int m_i2cAddr;
        int m_bus;
        mraa::I2c m_i2Ctx;

        uint16_t i2cReadReg_N (int reg, unsigned int len, uint8_t * buffer);
        mraa::Result i2cWriteReg_N (uint8_t reg, unsigned int len, uint8_t * buffer);
        uint8_t DECtoBSD (uint8_t data);
        uint8_t BCDtoDEC (uint8_t data);
};

}
