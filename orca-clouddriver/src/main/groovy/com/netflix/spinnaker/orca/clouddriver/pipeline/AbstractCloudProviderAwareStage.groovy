/*
 * Copyright 2015 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.orca.clouddriver.pipeline

import com.netflix.spinnaker.orca.pipeline.LinearStage
import com.netflix.spinnaker.orca.pipeline.model.Stage
import groovy.util.logging.Slf4j

/**
 * @author sthadeshwar
 */
@Slf4j
abstract class AbstractCloudProviderAwareStage extends LinearStage {

  private static final String DEFAULT_CLOUD_PROVIDER = "aws"  // TODO: Should we fetch this from configuration instead?

  AbstractCloudProviderAwareStage(String name) {
    super(name)
  }

  protected String getCloudProvider(Stage stage) {
    stage.context.cloudProvider ?: DEFAULT_CLOUD_PROVIDER
  }

  protected String getCredentials(Stage stage) {
    String credentials = stage.context."account.name"
    if (!credentials && stage.context.account) {
      credentials = stage.context.account
    } else if (!credentials && stage.context.credentials) {
      credentials = stage.context.credentials
    }
    credentials
  }
}