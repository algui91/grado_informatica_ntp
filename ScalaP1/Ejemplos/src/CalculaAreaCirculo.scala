/*
 * Copyright 2016 Alejandro Alcalde
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
  * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 3/14/16.
  */

def areaCirculo(r: String): Double = {
  r.isEmpty match {
    case true => 0
    case false => math.pow(r.toDouble, 2) * math.Pi
  }
}

if (args.length == 1) {
  // Conversión de tipos
//  val r = util.Try(args(0).toDouble).getOrElse(0)
  println("Área del círculo: " + areaCirculo(args(0)))
} else {
  println("uso: radio del círculo")
}
