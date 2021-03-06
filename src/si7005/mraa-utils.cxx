/*
 * Author: Henry Bruce <henry.bruce@intel.com>
 * Copyright (c) 2014 Intel Corporation.
 *
 * This program and the accompanying materials are made available under the
 * terms of the The MIT License which is available at
 * https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 */

#include <string>

#include "mraa-utils.hpp"
#include "mraa/gpio.hpp"

 #define UPM_THROW(msg) throw std::runtime_error(std::string(__FUNCTION__) + ": " + (msg))

void MraaUtils::setGpio(int pin, int level)
{
/*
   mraa_result_t status = MRAA_ERROR_NO_RESOURCES; 
   mraa_gpio_context gpio = mraa_gpio_init(pin);
   if (gpio != NULL) 
   {
      mraa_gpio_dir(gpio, MRAA_GPIO_OUT);
      status = mraa_gpio_write(gpio, level);
      mraa_gpio_close(gpio);
   } 
   return status;
*/
   mraa::Gpio gpio(pin);
   gpio.dir(mraa::DIR_OUT);
   if (gpio.write(level) != mraa::SUCCESS)
      UPM_THROW("gpio write failed");
}


int MraaUtils::getGpio(int pin)
{
/*
   mraa_result_t status = MRAA_ERROR_NO_RESOURCES; 
   mraa_gpio_context gpio = mraa_gpio_init(pin);
   if (gpio != NULL) 
   {
      status = mraa_gpio_dir(gpio, MRAA_GPIO_IN);
      int value = mraa_gpio_read(gpio);
      if (value != -1)
         *level = value;
      else
         status = MRAA_ERROR_NO_RESOURCES; 
      mraa_gpio_close(gpio);
   }
   return status;       
*/
   mraa::Gpio gpio(pin);
   gpio.dir(mraa::DIR_IN);
   return gpio.read();
}


