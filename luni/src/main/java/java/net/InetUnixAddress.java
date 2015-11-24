/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.net;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static android.system.OsConstants.*;

/**
 * An AF_UNIX address. See {@link InetAddress}.
 * @hide
 */
public final class InetUnixAddress extends InetAddress {
  private byte[] address;
  /**
   * Constructs an AF_UNIX InetAddress for the given path.
   */
  public InetUnixAddress(String path) {
    this(path.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Constructs an AF_UNIX InetAddress for the given path.
   */
  public InetUnixAddress(byte[] path) {
    super();
    holder().hostName = null;
    holder().family = AF_UNIX;
    address = path.clone();
  }

  /**
   * Returns a string form of this InetAddress.
   */
  @Override public String toString() {
    return "InetUnixAddress[" + new String(address, StandardCharsets.UTF_8) + "]";
  }

  @Override
  public byte[] getAddress() {
      return address.clone();
  }

  public byte[] getAddressInternal() {
      return address;
  }

  @Override
  public String getHostAddress() {
      return new String(address, StandardCharsets.UTF_8);
  }

  /**
   * Compares this {@code InetUnixAddress} instance against the specified address
   * in {@code obj}. Two addresses are equal if their address byte arrays have
   * the same length and if the bytes in the arrays are equal.
   *
   * @param obj
   *            the object to be tested for equality.
   * @return {@code true} if both objects are equal, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object obj) {
      if (obj == null ||
          !(obj instanceof InetUnixAddress))
        return false;

      return Arrays.equals(this.address, ((InetUnixAddress) obj).address);
  }

  /**
   * Returns a hashcode for this IP address.
   *
   * @return  a hash code value for this IP address.
   */
  @Override
  public int hashCode() {
    return Arrays.hashCode(address);
  }
  // ----- END android -----
}