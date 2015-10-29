/**
 * Copyright 2015 Otto (GmbH & Co KG)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.schedoscope

import org.schedoscope.AskPattern.actorSelectionToRef
import org.schedoscope.scheduler.actors.RootActor
import akka.actor.ActorSystem

/**
 * The Schedoscope object provides accessors for the various components of the schedoscope system.
 * It thus serves as an entry point to my system.
 */
object Schedoscope {

  /**
   * The Schedoscope actor system
   */
  val actorSystem = ActorSystem("schedoscope")

  /**
   * The Schedoscope settings.
   */
  val settings = Settings()

  /**
   * A reference to the Schedoscope root actor
   */
  val rootActor = actorSystem.actorOf(RootActor.props(settings), "root")

  /**
   * A reference to the Schedoscope view manager actor
   */
  lazy val viewManagerActor = actorSelectionToRef(actorSystem.actorSelection(rootActor.path.child("views"))).get

  /**
   * A reference to the Schedoscope schema actor
   */
  lazy val schemaActor = actorSelectionToRef(actorSystem.actorSelection(rootActor.path.child("schema"))).get

  /**
   * A reference to the Schedoscope partition creator actor
   */
  lazy val partitionCreatorActor = actorSelectionToRef(actorSystem.actorSelection(schemaActor.path.child("partition-creator"))).get

  /**
   * A reference to the Schedoscope metadata logger actor
   */
  lazy val metadataLoggerActor = actorSelectionToRef(actorSystem.actorSelection(schemaActor.path.child("metadata-logger"))).get

  /**
   * A reference to the Schedoscope transformation manager logger actor
   */
  lazy val transformationManagerActor = actorSelectionToRef(actorSystem.actorSelection(rootActor.path.child("transformations"))).get
}