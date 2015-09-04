/*
 * Copyright 2014 LinkedIn Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package azkaban.executor;

import azkaban.utils.Utils;

/**
 * Class to represent an AzkabanExecutorServer details for ExecutorManager
 *
 * @author gaggarwa
 */
public class Executor {
  private final int id;
  private final String host;
  private final int port;
  private boolean isActive;

  // TODO: ExecutorStats to be added

  /**
   * <pre>
   * Construct an Executor Object
   * Note: port should be a within unsigned 2 byte
   * integer range
   * </pre>
   *
   * @param executor_id
   * @param executor_host
   * @param executor_port
   */
  public Executor(int id, String host, int port, boolean isActive) {
    if (!Utils.isValidPort(port)) {
      throw new IllegalArgumentException(String.format(
        "Invalid port number %d for host %s, executor_id %d", port, host, id));
    }

    this.id = id;
    this.host = host;
    this.port = port;
    this.isActive = isActive;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (isActive ? 1231 : 1237);
    result = prime * result + ((host == null) ? 0 : host.hashCode());
    result = prime * result + id;
    result = prime * result + port;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof Executor))
      return false;
    Executor other = (Executor) obj;
    if (isActive != other.isActive)
      return false;
    if (host == null) {
      if (other.host != null)
        return false;
    } else if (!host.equals(other.host))
      return false;
    if (id != other.id)
      return false;
    if (port != other.port)
      return false;
    return true;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public boolean isActive() {
    return isActive;
  }

  public int getId() {
    return id;
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public String toString() {
    return String.format("[%d]%s:%d", id, host, port);
  }
}
